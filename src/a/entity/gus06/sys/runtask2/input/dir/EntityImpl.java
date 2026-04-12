package a.entity.gus06.sys.runtask2.input.dir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, G {

	public String creationDate() {return "20251212";}


	private Service taskChooser;
	private Service manager;
	
	private P task;


	public EntityImpl() throws Exception
	{
		taskChooser = Outside.service(this,"gus06.sys.runtask2.input.dir.chooser");
		manager = Outside.service(this,"gus06.sys.runtask1.manager");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File f = (File) obj;
		if(!f.isDirectory()) return;
		
		task = (P) taskChooser.g();
		if(task==null) return;
		
		manager.v(f.getAbsolutePath(),task);
	}
	
	
	public Object g() throws Exception
	{return task;}
}