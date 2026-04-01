package a.entity.gus06.java.jdk.currentdir;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140819";}

	
	private Service jdkDirs;
	
	
	private File dir;

	public EntityImpl() throws Exception
	{
		jdkDirs = Outside.service(this,"gus06.java.jdk.dirs");
	}
	
	
	public Object g() throws Exception
	{
		if(dir==null) init();
		return dir;
	}
	
	
	private void init() throws Exception
	{
		File[] dirs = (File[]) jdkDirs.g();
		if(dirs==null || dirs.length==0) return;
    		
		String jvmVersion = System.getProperty("java.vm.specification.version");
		String jdkStart = "jdk"+jvmVersion;
		
		for(int i=0;i<dirs.length;i++)
		if(dirs[i].getName().startsWith(jdkStart))
		{dir = dirs[i];return;}
	}
}
