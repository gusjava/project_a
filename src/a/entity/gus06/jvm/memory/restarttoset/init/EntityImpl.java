package a.entity.gus06.jvm.memory.restarttoset.init;

import java.io.File;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity {

	public String creationDate() {return "20190524";}

	public static final String KEY = "jvm.mem.max";
	
	private Service findJar;
	private Service findArgs;
	private Service launchJar;
	private Map props;


	public EntityImpl() throws Exception
	{
		findJar = Outside.service(this,"gus06.app.jarfile");
		findArgs = Outside.service(this,"gus06.app.argsline");
		launchJar = Outside.service(this,"gus06.java.launchjar");
		props = (Map) Outside.resource(this,"props");
	
		if(!props.containsKey(KEY)) return;
		long v = Long.parseLong((String) props.get(KEY));
		
		long max = Runtime.getRuntime().maxMemory();
		long total = Runtime.getRuntime().totalMemory();
		
		if(max==v && total==v) return;
		
		File jar = (File) findJar.g();
		String args = (String) findArgs.g();
		String options = "-Xms"+v+" -Xmx"+v;
		
		Map map = new HashMap();
		map.put("jar",jar);
		map.put("options",options);
		map.put("args",args);
		
		launchJar.p(map);
		System.exit(0);
	}
}
