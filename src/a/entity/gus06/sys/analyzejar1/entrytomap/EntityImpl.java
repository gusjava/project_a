package a.entity.gus06.sys.analyzejar1.entrytomap;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150702";}
	
	public static final String KEY_CLASSVERSION = "class.version";
	public static final String KEY_CLASSPATH = "class.path";
	public static final String KEY_CLASSMD5 = "class.md5";


	private Service classVersion;
	private Service toClasspath;
	private Service buildMd5;


	public EntityImpl() throws Exception
	{
		classVersion = Outside.service(this,"gus.x.file.class1.classversion");
		toClasspath = Outside.service(this,"gus.x.file.jar.entry.toclasspath");
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JarEntry entry = (JarEntry) o[0];
		JarFile jar = (JarFile) o[1];
		
		String classpath = (String) toClasspath.t(entry);
		
		int[] version = (int[]) classVersion.t(jar.getInputStream(entry));
		String md5 = (String) buildMd5.t(jar.getInputStream(entry));
		
		Map map = new HashMap();
		
		map.put(KEY_CLASSVERSION,version[0]+"-"+version[1]);
		map.put(KEY_CLASSPATH,classpath);
		map.put(KEY_CLASSMD5,md5);
		
		return map;
	}
}
