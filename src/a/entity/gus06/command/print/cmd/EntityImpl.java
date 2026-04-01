package a.entity.gus06.command.print.cmd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140703";}

	private Map prop;
	private Service printObject;

	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"prop");
		printObject = Outside.service(this,"gus06.print.object");
	}
	
	
	public void e() throws Exception
	{
		Map cmdMap = new HashMap();
		Iterator it = prop.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(key.startsWith("cmd."))
			cmdMap.put(key.substring(4),prop.get(key));
			
			if(key.startsWith("cmdalias."))
			cmdMap.put(key.substring(9),prop.get(key));
		}
		
		printObject.p(cmdMap);
	}
}
