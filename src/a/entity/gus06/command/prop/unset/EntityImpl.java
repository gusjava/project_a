package a.entity.gus06.command.prop.unset;

import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140703";}

	private Map prop;
	
	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"prop");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		prop.remove(s);
	}
}
