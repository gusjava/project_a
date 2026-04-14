package a.entity.gus06.web.allocine.api.movie;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141010";}

	
	private Service perform;
	private Service jsonParser;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.web.allocine.api.movie.perform");
		jsonParser = Outside.service(this,"gus.x.json.parse1");
	}

	public Object t(Object obj) throws Exception
	{
		String json = (String) perform.t(obj);
		return jsonParser.t(json);
	}
}
