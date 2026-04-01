package a.entity.gus06.appli.gusappmonitor.execute.app.kill;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190410";}

	public static final String INFO_RUNTIME_PID = "runtime_pid";
	

	private Service killPid;
	
	public EntityImpl() throws Exception
	{
		killPid = Outside.service(this,"gus06.env.windows.perform.taskkill");
	}
	
	
	public void p(Object obj) throws Exception
	{
		R config = (R) obj;
		Map infoMap = (Map) config.r("infoMap");
		
		String pid = get(infoMap,INFO_RUNTIME_PID);
		killPid.p(pid);
	}
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return (String) map.get(key);
	}
}
