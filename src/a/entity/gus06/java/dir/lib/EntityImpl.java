package a.entity.gus06.java.dir.lib;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140820";}

	
	private Service home;
	private File dir;
	
	
	public EntityImpl() throws Exception
	{
		home = Outside.service(this,"gus06.java.dir.home");
	}
	
	
	public Object g() throws Exception
	{
		if(dir==null) init();
		return dir;
	}
	
	
	private void init() throws Exception
	{
		File homeDir = (File) home.g();
		dir = new File(homeDir,"lib");
		
		if(!dir.isDirectory())
			throw new Exception("Java bin directory does not exist: "+dir);
	}
}
