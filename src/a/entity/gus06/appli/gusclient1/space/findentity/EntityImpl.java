package a.entity.gus06.appli.gusclient1.space.findentity;

import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140718";}


	private Map prop;
	
	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"prop");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String id = (String) obj;
		if(!prop.containsKey("space."+id))
			throw new Exception("Undefined space id: "+id);
		return prop.get("space."+id);
	}
}
