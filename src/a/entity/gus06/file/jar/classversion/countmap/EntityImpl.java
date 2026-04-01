package a.entity.gus06.file.jar.classversion.countmap;

import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150702";}


	private Service findClassVersion;

	public EntityImpl() throws Exception
	{
		findClassVersion = Outside.service(this,"gus06.file.class1.classversion");
	}



	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		JarFile jarFile = new JarFile(file);
		
		Map map = new HashMap();
		
		Enumeration en = jarFile.entries();
		while(en.hasMoreElements())
		{
			JarEntry entry = (JarEntry) en.nextElement();
			if(isClassEntry(entry))
			{
				int version = version(jarFile,entry);
				addToMap(map,""+version);
			}
		}
		
		jarFile.close();
		return map;
	}

	
	
	private boolean isClassEntry(JarEntry entry)
	{
		if(entry.isDirectory()) return false;
		return entry.getName().toLowerCase().endsWith(".class");
	}
	


	private int version(JarFile jarFile, JarEntry entry) throws Exception
	{
		InputStream is = jarFile.getInputStream(entry);
		int[] v = (int[]) findClassVersion.t(is);
		return v[0];
	}
	
	
	
	private void addToMap(Map map, String key)
	{
		if(!map.containsKey(key))
			map.put(key,Integer.valueOf(1));
		else
		{
			int n = ((Integer) map.get(key)).intValue();
			map.put(key,Integer.valueOf(n+1));
		}
	}
}
