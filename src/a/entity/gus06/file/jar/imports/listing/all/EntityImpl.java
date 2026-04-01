package a.entity.gus06.file.jar.imports.listing.all;

import a.framework.*;
import java.io.File;
import java.util.jar.JarFile;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.io.InputStream;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170223";}
	



	private Service extractImports;

	public EntityImpl() throws Exception
	{
		extractImports = Outside.service(this,"gus06.java.bytecode.jdepend.analyze.imports");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		JarFile jar = null;
		
		Set set = new HashSet();
		
		try
		{
			jar = new JarFile(file,true,JarFile.OPEN_READ);
			
			Enumeration en = jar.entries();
			while(en.hasMoreElements())
			{
				JarEntry entry = (JarEntry) en.nextElement();
				if(isClass(entry.getName()))
				{
					InputStream is = jar.getInputStream(entry);
					Set imports = (Set) extractImports.t(is);
					set.addAll(imports);
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
		
		List list = new ArrayList(set);
		Collections.sort(list);
		return list;
	}
	
	
	
	private boolean isClass(String name)
	{return name.endsWith(".class");}
}
