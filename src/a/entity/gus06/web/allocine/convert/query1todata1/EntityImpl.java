package a.entity.gus06.web.allocine.convert.query1todata1;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200112";}
	
	public static final String KEY_CODE = "code";


	private Service codeToMovie1;
	private Service performSearch;
	private Service formatOutput;

	public EntityImpl() throws Exception
	{
		codeToMovie1 = Outside.service(this,"gus06.web.allocine.convert.codetomovie1");
		performSearch = Outside.service(this,"gus06.web.allocine.convert.query1tosearch");
		formatOutput = Outside.service(this,"gus06.web.allocine.convert.query1todata1.format");
	}
	
	public Object t(Object obj) throws Exception
	{
		String query = (String) obj;
		Map map = queryToMap(query);
		return map!=null ? formatOutput.t(map) : null;
	}
	
	
	private Map queryToMap(String query) throws Exception
	{
		if(query.startsWith(">"))
		{
			String code = query.substring(1);
			Map map2 = (Map) codeToMovie1.t(code);
			
			String title = (String) map2.get("originalTitle");
			Map map1 = (Map) performSearch.t(title);
			if(map1==null) throw new Exception("Allocine query empty with title="+title);
			
			map1.putAll(map2);
			return map1;
		}
		else
		{
			Map map1 = (Map) performSearch.t(query);
			if(map1==null) return null;
			
			String code = (String) map1.get(KEY_CODE);
			Map map2 = (Map) codeToMovie1.t(code);
			if(map2==null) throw new Exception("Allocine movie not found with code="+code);
			
			map1.putAll(map2);
			return map1;
		}
	}
}
