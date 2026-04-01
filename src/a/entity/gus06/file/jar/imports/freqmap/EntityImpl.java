package a.entity.gus06.file.jar.imports.freqmap;

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

	public String creationDate() {return "20170223";}
	



	private Service extractImports;
	private Service appendToMap;

	public EntityImpl() throws Exception
	{
		extractImports = Outside.service(this,"gus06.java.bytecode.jdepend.analyze.imports");
		appendToMap = Outside.service(this,"gus06.map.freqmap.appendall");
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
					InputStream is = jar.getInputStream(entry);
					Set imports = (Set) extractImports.t(is);
					appendToMap.p(new Object[]{map,imports});
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
