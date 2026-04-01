package a.entity.gus06.file.jar.findpackages;

import a.framework.*;
import java.util.*;
import java.io.File;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170223";}


	private Service toPackage;

	public EntityImpl() throws Exception
	{
		toPackage = Outside.service(this,"gus06.file.jar.entry.topackage");
	}



	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		JarFile jarFile = new JarFile(file,true,JarFile.OPEN_READ);
		
		Set set = new HashSet();
		Enumeration en = jarFile.entries();
		while(en.hasMoreElements())
		{
			JarEntry entry = (JarEntry) en.nextElement();
			if(isClass(entry.getName()))
			{
				String package1 = (String) toPackage.t(entry);
				set.add(package1);
			}
		}
		jarFile.close();
		
		List list = new ArrayList(set);
		Collections.sort(list);
		return list;
	}
	
	
	private boolean isClass(String name)
	{return name.endsWith(".class");}
}
