package a.entity.gus06.appli.gusappmonitor.execute.app.start;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190408";}

	public static final String INFO_JAR_PATH = "jar_path";
	public static final String INFO_JAR_OPTIONS = "jar_options";
	public static final String INFO_JAR_ARGS = "jar_args";
	

	private Service launchJar;
	
	public EntityImpl() throws Exception
	{
		launchJar = Outside.service(this,"gus06.java.launchjar");
	}
	
	
	public void p(Object obj) throws Exception
	{
		R config = (R) obj;
		Map infoMap = (Map) config.r("infoMap");
		Map propMap = (Map) config.r("propMap");
		
		Map m = new HashMap();
		
		String jarPath = get1(infoMap,INFO_JAR_PATH);
		m.put("jar",jarPath);
		
		String jarArgs = get(propMap,INFO_JAR_ARGS);
		if(jarArgs!=null) m.put("args",jarArgs);
		
		String jarOptions = get(propMap,INFO_JAR_OPTIONS);
		if(jarOptions!=null) m.put("options",jarOptions);
		
		launchJar.p(m);
	}
	
	
	private String get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return (String) map.get(key);
	}
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}
