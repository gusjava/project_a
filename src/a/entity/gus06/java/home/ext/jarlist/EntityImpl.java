package a.entity.gus06.java.home.ext.jarlist;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;


public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140820";}

	private Service findDirs;
	private ArrayList files;
	
	private FileFilter jarFilter = new FileFilter() {
		public boolean accept(File file)
		{return file.isFile() && file.getName().endsWith(".jar");}
	};
	
	
	public EntityImpl() throws Exception
	{
		findDirs = Outside.service(this,"gus06.java.home.ext.dirs");
	}
	
	
	public Object g() throws Exception
	{
		if(files==null) init();
		return files;
	}
	
	
	private void init() throws Exception
	{
		File[] dirs = (File[]) findDirs.g();
		files = new ArrayList();
		
		for(File dir:dirs)
		if(dir.isDirectory())
		{
			File[] jars = dir.listFiles(jarFilter);
			if(jars!=null) for(File jar:jars) files.add(jar);
		}
	}
}
