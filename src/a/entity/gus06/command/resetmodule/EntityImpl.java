package a.entity.gus06.command.resetmodule;

import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140707";}

	
	private Map main;

	public EntityImpl() throws Exception
	{
		main = (Map) Outside.resource(this,"main");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String name = (String) obj;
		
		if(!main.containsKey(name))
			throw new Exception("Invalid module name: "+name);
		Class c = Class.forName(name);
		main.put(name,c.newInstance());
	}
}
