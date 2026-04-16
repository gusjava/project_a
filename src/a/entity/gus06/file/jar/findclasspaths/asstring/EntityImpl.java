package a.entity.gus06.file.jar.findclasspaths.asstring;

import a.framework.*;
import java.io.File;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190305";}


	private Service toClasspath;

	public EntityImpl() throws Exception
	{
		toClasspath = Outside.service(this,"gus.x.file.jar.entry.toclasspath");
	}



	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		JarFile jarFile = new JarFile(file,true,JarFile.OPEN_READ);

		StringBuilder b = new StringBuilder();
		Enumeration en = jarFile.entries();
		while(en.hasMoreElements())
		{
			JarEntry entry = (JarEntry) en.nextElement();
			if(isClass(entry.getName()))
			{
				String classpath = (String) toClasspath.t(entry);
				b.append(classpath);
				b.append("\n");
			}
		}
		jarFile.close();
		return b.toString();
	}
	
	
	private boolean isClass(String name)
	{return name.endsWith(".class");}
}
