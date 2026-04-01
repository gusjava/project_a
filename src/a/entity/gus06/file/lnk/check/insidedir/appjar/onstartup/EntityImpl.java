package a.entity.gus06.file.lnk.check.insidedir.appjar.onstartup;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20180309";}


	private Service check;
	private Service findStartupFolder;

	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.file.lnk.check.insidedir.appjar");
		findStartupFolder = Outside.service(this,"gus06.env.windows.folder.startup");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		File dir = (File) findStartupFolder.g();
		return check.f(dir);
	}
}
