package a.entity.gus06.command.backupapp;

import java.io.File;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, E {

	public String creationDate() {return "20140721";}


	public static final String PATH = "path.backupdir";
	

	private Service copy;
	private Service jarMd5;
	private File dir;
	
	
	public EntityImpl() throws Exception
	{
		copy = Outside.service(this,"gus06.app.jarfile.copy");
		jarMd5 = Outside.service(this,"gus06.app.jarfile.md5.c");
		
		dir = (File) Outside.resource(this,"path#"+PATH);
		if(dir==null) throw new Exception("Undefined path: "+PATH);
		dir.mkdirs();
	}
	
	
	
	public void e() throws Exception
	{
		start();
		
		try
		{
			String md5 = (String) jarMd5.g();
			File file = new File(dir,md5);
			if(!file.exists()) copy.p(file);
		}
		finally {end();}
	}
	
	
	
	private void start()
	{send(this,"start()");}
	
	private void end()
	{send(this,"end()");}
}
