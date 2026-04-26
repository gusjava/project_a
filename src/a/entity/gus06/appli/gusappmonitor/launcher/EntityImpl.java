package a.entity.gus06.appli.gusappmonitor.launcher;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.HashMap;

public class EntityImpl implements Entity {

	public String creationDate() {return "20190313";}
	
	
	public static final String KEY_START = "start_oninit";
	
	public static final String INFO_JAR_PATH = "jar_path";
	public static final String INFO_JAR_OPTIONS = "jar_options";
	public static final String INFO_JAR_ARGS = "jar_args";
	
	public static final String FILENAME_INFO = "info.properties";
	public static final String FILENAME_PROP = "prop.properties";
	

	private Service manager;
	private Service consoleGui;
	private Service launchJar;
	private Service readProp;
	

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusappmonitor.manager");
		consoleGui = Outside.service(this,"gus06.appli.gusappmonitor.gui.console");
		launchJar = Outside.service(this,"gus06.java.launchjar");
		readProp = Outside.service(this,"gus.x.file.prop.read");
		
		File rootDir = (File) manager.r("rootDir");
		File[] dirs = rootDir.listFiles();
		
		if(dirs!=null) for(File dir : dirs)
		handleDir(dir);
	}
	
	
	
	private void handleDir(File dir) throws Exception
	{
		File propFile = new File(dir,FILENAME_PROP);
		Map propMap = (Map) readProp.t(propFile);
		if(propMap==null) return;
		
		File infoFile = new File(dir,FILENAME_INFO);
		Map infoMap = (Map) readProp.t(infoFile);
		if(infoMap==null) return;
		
		String start = get(propMap,KEY_START);
		if(start==null || !start.equals("true")) return;
		
		String jarPath = get(infoMap,INFO_JAR_PATH);
		if(jarPath==null) return;
		File jarFile = new File(jarPath);
		if(!jarFile.isFile()) return;
		
		Map m = new HashMap();
		
		m.put("jar",jarFile);
		
		String jarArgs = get(propMap,INFO_JAR_ARGS);
		if(jarArgs!=null) m.put("args",jarArgs);
		
		String jarOptions = get(propMap,INFO_JAR_OPTIONS);
		if(jarOptions!=null) m.put("options",jarOptions);
		
		launchJar.p(m);
	}
	
	
	
	private void launchJar(Map m)
	{
		try
		{
			println("starting "+m.get("jar"));
			launchJar.p(m);
		}
		catch(Exception e)
		{Outside.err(this,"launchJar(Map)",e);}
	}
	
	
	
	private void println(String line) throws Exception
	{consoleGui.p(line);}
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}
