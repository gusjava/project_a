package a.entity.gus06.sys.analyzejar1.analyzer;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.Enumeration;
import java.util.jar.JarEntry;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170223";}
	
	public static final String KEY_JARMD5 = "jar.md5";
	public static final String KEY_JARNAME = "jar.name";



	private Service entryToMap;
	private Service writeToDir;
	private Service buildMd5;


	public EntityImpl() throws Exception
	{
		entryToMap = Outside.service(this,"gus06.sys.analyzejar1.entrytomap");
		writeToDir = Outside.service(this,"gus06.dir.access.write.properties.randomid");
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = o[0];
		File dir = o[1];
		
		String jarmd5 = (String) buildMd5.t(file);
		String jarname = file.getName();
		
		JarFile jar = null;
		
		try
		{
			jar = new JarFile(file,true,JarFile.OPEN_READ);
			
			Enumeration en = jar.entries();
			while(en.hasMoreElements())
			{
				JarEntry entry = (JarEntry) en.nextElement();
				Map map = handleEntry(entry,jar);
				
				if(map!=null)
				{
					map.put(KEY_JARMD5,jarmd5);
					map.put(KEY_JARNAME,jarname);
					writeToDir.p(new Object[]{dir,map});
				}
			}
		}
		catch(Exception e)
		{
			String message = "Jar file failed: "+file;
			throw new Exception(message,e);
		}
		finally
		{
			if(jar!=null) jar.close();
		}
	}
		
	
	
	
	
	
	private Map handleEntry(JarEntry entry, JarFile jar)
	{
		try
		{
			if(!entry.getName().endsWith(".class")) return null;
			return (Map) entryToMap.t(new Object[]{entry,jar});
		}
		catch(Exception e)
		{Outside.err(this,"handleEntry(JarEntry,JarFile)",e);}
		return null;
	}
}
