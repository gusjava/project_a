package a.entity.gus06.java.launchbin;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20260409";}

	public static final String KEY_CLASSPATH = "classpath";
	public static final String KEY_MAINCLASS = "mainclass";
	public static final String KEY_ARGS      = "args";
	public static final String KEY_OPTIONS   = "options";


	private Service findExe;
	private Service pClasspath;
	private Service pMainclass;
	private Service pArgs;
	private Service pOptions;


	public EntityImpl() throws Exception
	{
		findExe    = Outside.service(this, "gus06.java.dir.bin.javaexe");
		pClasspath = Outside.service(this, "gus06.java.launchbin.p.classpath");
		pMainclass = Outside.service(this, "gus06.java.launchbin.p.mainclass");
		pArgs      = Outside.service(this, "gus06.java.launchjar.p.args");
		pOptions   = Outside.service(this, "gus06.java.launchjar.p.options");
	}


	public void p(Object obj) throws Exception
	{t(obj);}


	public Object t(Object obj) throws Exception
	{
		List cmd = new ArrayList();

		File javaExe = (File) findExe.g();
		cmd.add(javaExe.getAbsolutePath());

		handleData(cmd, obj);

		ProcessBuilder pb = new ProcessBuilder(cmd);
		pb.redirectErrorStream(true);
		return pb.start();
	}


	private void handleData(List list, Object obj) throws Exception
	{
		if(obj instanceof Map)
		{
			Map map = (Map) obj;

			Object options   = get(map, KEY_OPTIONS);
			Object classpath = get1(map, KEY_CLASSPATH);
			Object mainclass = get1(map, KEY_MAINCLASS);
			Object args      = get(map, KEY_ARGS);

			pOptions.p(new Object[]{list, options});
			pClasspath.p(new Object[]{list, classpath});
			pMainclass.p(new Object[]{list, mainclass});
			pArgs.p(new Object[]{list, args});

			return;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}


	public Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}

	public Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
}
