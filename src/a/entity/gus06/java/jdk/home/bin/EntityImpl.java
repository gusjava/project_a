package a.entity.gus06.java.jdk.home.bin;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20171031";}

	
	private Service home;
	private File dir;
	
	
	public EntityImpl() throws Exception
	{
		home = Outside.service(this,"gus06.java.jdk.home");
	}
	
	
	public Object g() throws Exception
	{
		if(dir==null) init();
		return dir;
	}
	
	
	private void init() throws Exception
	{
		File homeDir = (File) home.g();
		dir = new File(homeDir,"bin");
		
		if(!dir.isDirectory())
			throw new Exception("JDK bin directory does not exist: "+dir);
	}
}
