package a.entity.gus06.java.searchclass.finder;

import a.framework.*;
import java.io.File;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.List;
import java.util.ArrayList;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140819";}

	public static String JARPATHSEP = "/";


	private Service findJars;

	public EntityImpl() throws Exception
	{findJars = Outside.service(this,"gus06.java.searchclass.jarlist");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		F filter = (F) obj;
		List jars = (List) findJars.g();
		
		ArrayList list = new ArrayList();
		for(Object jar:jars) searchIntoFile(list,(File)jar,filter);
		return list;
	}
	
	
	
	
	private void searchIntoFile(ArrayList list, File file, F filter) throws Exception
	{
		JarFile jar = null;
		try
		{
			jar = new JarFile(file,true,JarFile.OPEN_READ);
			Enumeration en = jar.entries();
			while(en.hasMoreElements())
			{
				JarEntry entry = (JarEntry) en.nextElement();
				String classpath = entryToClass(entry,filter);
        			if(classpath!=null) list.add(classpath);
			}
		}
		finally{if(jar!=null) jar.close();}
	}
	
	
	
	
	private String entryToClass(JarEntry entry, F filter) throws Exception
	{
		if(entry.isDirectory()) return null;
		
		String entryName = entry.getName();
		if(!entryName.endsWith(".class")) return null;
		//if(entryName.contains("$")) return null;
		
		String classpath = toClasspath(entryName);
		if(filter.f(classpath)) return classpath;
		
		return null;
	}
	
	

	private String toClasspath(String entryName)
	{return entryName.substring(0,entryName.length()-6).replace(JARPATHSEP,".");}
}
