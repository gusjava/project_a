package a.entity.gus06.java.home.ext.dirs;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140820";}

	private File[] dirs;
	
	public EntityImpl() throws Exception
	{}
	
	
	public Object g() throws Exception
	{
		if(dirs==null) init();
		return dirs;
	}
	
	
	private void init() throws Exception
	{
		String ext = System.getProperty("java.ext.dirs");
		String[] n = ext!=null ? ext.split(File.pathSeparator) : new String[0];
		
		dirs = new File[n.length];
		for(int i=0;i<n.length;i++) dirs[i] = new File(n[i]); 
	}
}
