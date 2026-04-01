package a.entity.gus06.app.init.log.findfile;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20141023";}


	private Service getNow;

	private File dir;
	private File file;
	

	public EntityImpl() throws Exception
	{
		getNow = Outside.service(this,"gus06.time.now");
		dir = (File) Outside.resource(this,"path#path.logdir");
	}
	
	public Object g() throws Exception
	{
		if(file==null) init();
		return file;
	}
	
	private void init() throws Exception
	{
		String fileName = "log_"+getNow.g()+".txt";
		file = new File(dir,fileName);
		dir.mkdirs();
	}
}
