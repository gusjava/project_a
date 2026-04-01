package a.entity.gus06.java.jdk.manager;

import java.io.File;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, P {

	public String creationDate() {return "20140722";}

	private Service jdkDirs;
	private Service jdkLatest;
	private Service filePersister;
	
	private File dir;
	

	public EntityImpl() throws Exception
	{
		jdkDirs = Outside.service(this,"gus06.java.jdk.dirs");
		jdkLatest = Outside.service(this,"gus06.java.jdk.latestdir");
		filePersister = Outside.service(this,"gus06.app.persister1.data.file");
		
		dir = (File) filePersister.r(getClass().getName());
	}
	
	
	public Object g() throws Exception
	{
		if(dir==null) init();
		return dir;
	}
	
	
	public void p(Object obj) throws Exception
	{
		dir = (File) obj;
		filePersister.v(getClass().getName(),dir);
	}
	
	
	
	private void init() throws Exception
	{
		dir = (File) jdkLatest.g();
	}
}
