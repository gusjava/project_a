package a.entity.gus06.java.home.classpath.jarlist;

import a.framework.*;
import java.io.File;
import java.util.ArrayList;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140820";}

	private ArrayList files;
	
	
	public Object g() throws Exception
	{
		if(files==null) init();
		return files;
	}
	
	
	private void init() throws Exception
	{
		String ext = System.getProperty("java.class.path");
		String[] n = ext.split(File.pathSeparator);
		
		files = new ArrayList();
		for(int i=0;i<n.length;i++) files.add(new File(n[i])); 
	}
}
