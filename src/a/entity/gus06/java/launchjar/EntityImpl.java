package a.entity.gus06.java.launchjar;

import java.io.File;
import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;

import a.framework.*;
import java.util.Map;


public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20140705";}
	
	public static final String KEY_JAR = "jar";
	public static final String KEY_ARGS = "args";
	public static final String KEY_OPTIONS = "options";

	
	private Service findExe;
	private Service pJar;
	private Service pArgs;
	private Service pOptions;
	private PrintStream out;

	
	public EntityImpl() throws Exception
	{
		findExe = Outside.service(this,"gus06.java.dir.bin.javaexe");
		pJar = Outside.service(this,"gus06.java.launchjar.p.jar");
		pArgs = Outside.service(this,"gus06.java.launchjar.p.args");
		pOptions = Outside.service(this,"gus06.java.launchjar.p.options");
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		List cmd = new ArrayList();
		
		File javaExe = (File) findExe.g();
		cmd.add(javaExe.getAbsolutePath());
		
		handleData(cmd,obj);
		
		ProcessBuilder pb = new ProcessBuilder(cmd);
		pb.redirectErrorStream(true);
		return pb.start();
	}
	
	
	
	
	private void handleData(List list, Object obj) throws Exception
	{
		if(obj instanceof File)
		{
			pJar.p(new Object[]{list,obj});
			return;
		}
		if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			
			pJar.p(new Object[]{list,o[0]});
			pArgs.p(new Object[]{list,o[1]});
			return;
		}
		if(obj instanceof Map)
		{
			Map map = (Map) obj;
			
			Object options = get(map,KEY_OPTIONS);
			Object jar = get1(map,KEY_JAR);
			Object args = get(map,KEY_ARGS);
			
			pOptions.p(new Object[]{list,options});
			pJar.p(new Object[]{list,jar});
			pArgs.p(new Object[]{list,args});
			
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
