package a.entity.gus06.appli.gusclient1.tool.findentity;

import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140814";}


	private Map prop;
	
	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"prop");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String id = (String) obj;
		if(!prop.containsKey("tool."+id))
			throw new Exception("Undefined tool id: "+id);
		return prop.get("tool."+id);
	}
}
