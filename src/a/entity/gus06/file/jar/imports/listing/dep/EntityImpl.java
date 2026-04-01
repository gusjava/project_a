package a.entity.gus06.file.jar.imports.listing.dep;

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
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170223";}
	

	private Service toPackage;
	private Service extractImports;
	private Service isRtClass;

	public EntityImpl() throws Exception
	{
		toPackage = Outside.service(this,"gus06.file.jar.entry.topackage");
		extractImports = Outside.service(this,"gus06.java.bytecode.jdepend.analyze.imports");
		isRtClass = Outside.service(this,"gus06.java.isclass.rt");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		JarFile jar = null;
		
		Set set1 = new HashSet();
		Set set2 = new HashSet();
		
		try
		{
			jar = new JarFile(file,true,JarFile.OPEN_READ);
			
			int done = 0;
			Enumeration en = jar.entries();
			while(en.hasMoreElements())
			{
				JarEntry entry = (JarEntry) en.nextElement();
				if(isClass(entry.getName()))
				{
					handleEntry(set1,set2,jar,entry,done);
					done++;
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
		
		List list = new ArrayList();
		
		Iterator it = set2.iterator();
		while(it.hasNext())
		{
			String import1 = (String) it.next();
			if(!isRtClass.f(import1) && !set1.contains(import1))
			list.add(import1);
		}
		
		Collections.sort(list);
		return list;
	}
	
	
	
	
	
	private void handleEntry(Set set1, Set set2, JarFile jar, JarEntry entry, int done) throws Exception
	{
		try
		{
			String package1 = (String) toPackage.t(entry);
			InputStream is = jar.getInputStream(entry);
			Set imports = (Set) extractImports.t(is);
			
			set1.add(package1);
			set2.addAll(imports);
		}
		catch(Exception e)
		{
			String message = "JarEntry failed: "+entry.getName()+" (done="+done+")";
			throw new Exception(message,e);
		}
	}
	
	
	
	
	private boolean isClass(String name)
	{return name.endsWith(".class");}
}
