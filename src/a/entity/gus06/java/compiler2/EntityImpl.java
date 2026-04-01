package a.entity.gus06.java.compiler2;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161107";}
	
	public static final String KEY_SRCDIR = "src";
	public static final String KEY_BINDIR = "bin";
	public static final String KEY_LIBDIR = "lib";
	public static final String KEY_JDKDIR = "jdk";
	public static final String KEY_FILTER = "filter";
	public static final String KEY_OUTPUT = "output";


	private Service findCompiler;
	
	public EntityImpl() throws Exception
	{
		findCompiler = Outside.service(this,"gus06.java.compiler.vx");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		File srcDir = (File) get1(map,KEY_SRCDIR);
		File binDir = (File) get1(map,KEY_BINDIR);
		
		File libDir = (File) get0(map,KEY_LIBDIR);
		File jdkDir = (File) get0(map,KEY_JDKDIR);
		
		F filter = (F) get0(map,KEY_FILTER);
		Object output = get0(map,KEY_OUTPUT);
		
		Object compiler = findCompiler.g();
		
		((V) compiler).v("srcDir",srcDir);
		((V) compiler).v("binDir",binDir);
		((V) compiler).v("libDir",libDir);
		((V) compiler).v("jdkDir",jdkDir);
		((V) compiler).v("filter",filter);
		((V) compiler).v("output",output);
		((E) compiler).e();
	}
	
	
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
	
	private Object get0(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
