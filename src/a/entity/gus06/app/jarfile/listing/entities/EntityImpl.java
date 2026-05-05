package a.entity.gus06.app.jarfile.listing.entities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140828";}

	private Service appJar;
	private List list;

	public EntityImpl() throws Exception
	{
		appJar = Outside.service(this,"gus.x.app.location.asjar");
	}
	
	
	public synchronized Object g() throws Exception
	{
		if(list==null) init();
		return new ArrayList(list);
	}
	
	
	private void init() throws Exception
	{
		list = new ArrayList();
		
		File file = (File) appJar.g();
		if(file==null) return;
		
		JarFile jar = new JarFile(file,true,JarFile.OPEN_READ);
		
		Enumeration en = jar.entries();
		while(en.hasMoreElements())
		{
			JarEntry entry = (JarEntry)en.nextElement();
			String entityName = entryToEntityName(entry);
			if(entityName!=null) list.add(entityName);
		}
		jar.close();
		
		Collections.sort(list);
	}
	
	
	
	public static final String START = "gus06/entity/";
	public static final String END = "/EntityImpl.class";
	
	private String entryToEntityName(JarEntry entry)
	{
		if(entry.isDirectory()) return null;
		
		String name = entry.getName();
		if(!name.startsWith(START)) return null;
		if(!name.endsWith(END)) return null;
		
		return name.substring(START.length(),name.length()-END.length()).replace("/",".");
	}
}
