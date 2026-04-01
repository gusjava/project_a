package a.entity.gus06.file.lnk.remove.insidedir.appjar.onstartup;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20180309";}


	private Service remove;
	private Service findStartupFolder;

	public EntityImpl() throws Exception
	{
		remove = Outside.service(this,"gus06.file.lnk.remove.insidedir.appjar");
		findStartupFolder = Outside.service(this,"gus06.env.windows.folder.startup");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		File dir = (File) findStartupFolder.g();
		return remove.f(dir);
	}
	
	public void p(Object obj) throws Exception
	{f(obj);}
}
