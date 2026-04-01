package a.entity.gus06.file.jar.imports.byclass;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150702";}
	



	private Service toClasspath;
	private Service extractImports;

	public EntityImpl() throws Exception
	{
		toClasspath = Outside.service(this,"gus06.file.jar.entry.toclasspath");
		extractImports = Outside.service(this,"gus06.java.bytecode.jdepend.analyze.imports");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		JarFile jar = null;
		Map map = new HashMap();
		
		try
		{
			jar = new JarFile(file,true,JarFile.OPEN_READ);
			
			Enumeration en = jar.entries();
			while(en.hasMoreElements())
			{
				JarEntry entry = (JarEntry) en.nextElement();
				if(isClass(entry.getName()))
				{
					String classpath = (String) toClasspath.t(entry);
					InputStream is = jar.getInputStream(entry);
					Set imports = (Set) extractImports.t(is);
					map.put(classpath,imports);
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
		
		return map;
	}
	
	
	
	private boolean isClass(String name)
	{return name.endsWith(".class");}
}
