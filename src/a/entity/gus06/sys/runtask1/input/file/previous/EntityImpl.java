package a.entity.gus06.sys.runtask1.input.file.previous;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220501";}


	private Service runTask;
	private Service manager;

	public EntityImpl() throws Exception
	{
		runTask = Outside.service(this,"gus06.sys.runtask1.input.file");
		manager = Outside.service(this,"gus06.sys.runtask1.manager");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File f = (File) obj;
		if(!f.isFile()) return;
		
		P previousTask = (P) runTask.g();
		if(previousTask!=null) manager.v(f.getAbsolutePath(),previousTask);
		else runTask.p(f);
	}
}