package a.entity.gus06.web.allocine.convert.query1tomovie3;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200112";}
	
	public static final String KEY_CODE = "code";


	private Service codeToMovie;
	private Service performSearch;

	public EntityImpl() throws Exception
	{
		codeToMovie = Outside.service(this,"gus06.web.allocine.convert.codetomovie3");
		performSearch = Outside.service(this,"gus06.web.allocine.convert.query1tosearch");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) performSearch.t(obj);
		String code = (String) map.get(KEY_CODE);
		return codeToMovie.t(code);
	}
}
