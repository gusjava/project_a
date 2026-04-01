package a.entity.gus06.entitydev.deploy.buildjar;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140727";}

	private Service jarBuilder;
	private Service getClassFiles;
	private Service getJavaFiles;
	private Service getJarFile;

	public EntityImpl() throws Exception
	{
		jarBuilder = Outside.service(this,"gus06.file.jar.builder1");
		getClassFiles = Outside.service(this,"gus06.entitydev.retrieve.classfile1.all");
		getJavaFiles = Outside.service(this,"gus06.entitydev.retrieve.javafile1.all");
		getJarFile = Outside.service(this,"gus06.entitydev.retrieve.jarfile1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String entityName = (String) obj;
	
		File[] classFiles = (File[]) getClassFiles.t(entityName);
		File[] javaFiles = (File[]) getJavaFiles.t(entityName);
		File jarFile = (File) getJarFile.t(entityName);
		
		if(classFiles==null) throw new Exception("classFiles is null for entity: "+entityName);
		if(javaFiles==null) throw new Exception("javaFiles is null for entity: "+entityName);
		if(jarFile==null) throw new Exception("jarFile is null for entity: "+entityName);
		
		Map entry = new HashMap();
		
		for(File f:classFiles) add(entry,entityName,f);
		for(File f:javaFiles) add(entry,entityName,f);
		
		jarBuilder.v("jarFile",jarFile);
		jarBuilder.v("entryMap",entry);
		jarBuilder.v("mainClass",null);
		jarBuilder.v("bin",null);
		
		jarBuilder.e();
	}
	
	
	
	private void add(Map entry, String entityName, File file)
	{
		String path = "gus06/entity/"+entityName.replace('.','/');
		String entryName = path+'/'+file.getName();
		entry.put(entryName,file);
	}
}
