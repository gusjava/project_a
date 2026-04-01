package a.entity.gus06.command.alias;

import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140718";}


	private Map prop;
	
	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"prop");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String cmd = (String) obj;
		String[] n = cmd.split("=",2);
		if(n.length!=2) throw new Exception("Invalid alias command: "+cmd);
		
		prop.put("cmdalias."+n[0],n[1]);
	}
}
