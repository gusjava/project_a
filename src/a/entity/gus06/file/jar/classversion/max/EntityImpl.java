package a.entity.gus06.file.jar.classversion.max;

import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150702";}


	
	private Service findClassVersion;

	public EntityImpl() throws Exception
	{
		findClassVersion = Outside.service(this,"gus.x.file.class1.classversion");
	}



	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		JarFile jarFile = new JarFile(file);
		
		int maxVersion = 0;
		
		Enumeration en = jarFile.entries();
		while(en.hasMoreElements())
		{
			JarEntry entry = (JarEntry) en.nextElement();
			if(isClassEntry(entry))
			{
				int version = version(jarFile,entry);
				if(version > maxVersion) maxVersion = version;
			}
		}
		
		jarFile.close();
		return ""+maxVersion;
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
}
