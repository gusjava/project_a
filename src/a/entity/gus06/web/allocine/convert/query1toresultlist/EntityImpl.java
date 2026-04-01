package a.entity.gus06.web.allocine.convert.query1toresultlist;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201020";}


	private Service codeToMovie1;
	private Service apiSearch;

	public EntityImpl() throws Exception
	{
		codeToMovie1 = Outside.service(this,"gus06.web.allocine.convert.codetomovie1");
		apiSearch = Outside.service(this,"gus06.web.allocine.api.search");
	}
	
	public Object t(Object obj) throws Exception
	{
		String query = (String) obj;
		if(query.startsWith(">"))
		{
			String code = query.substring(1);
			Map movieMap = (Map) codeToMovie1.t(code);
			String title = (String) movieMap.get("originalTitle");
			return apiSearch.t(title);
		}
		else
		{
			return apiSearch.t(query);
		}
	}
}
