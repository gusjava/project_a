package a.entity.gus06.file.jar.findclasspaths;

import a.framework.*;
import java.util.*;
import java.io.File;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170223";}


	private Service toClasspath;

	public EntityImpl() throws Exception
	{
		toClasspath = Outside.service(this,"gus.x.file.jar.entry.toclasspath");
	}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		JarFile jarFile = new JarFile(file,true,JarFile.OPEN_READ);

		List list = new ArrayList();
		Enumeration en = jarFile.entries();
		while(en.hasMoreElements())
		{
			JarEntry entry = (JarEntry) en.nextElement();
			if(isClass(entry.getName()))
			{
				String classpath = (String) toClasspath.t(entry);
				if(!list.contains(classpath)) list.add(classpath);
			}
		}
		Collections.sort(list);
		jarFile.close();
		return list;
	}
	
	
	private boolean isClass(String name)
	{return name.endsWith(".class");}
}
