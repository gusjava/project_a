package a.entity.gus06.file.lnk.remove.insidedir.appjar.ondesktop;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20180309";}


	private Service remove;
	private Service findDesktopFolder;

	public EntityImpl() throws Exception
	{
		remove = Outside.service(this,"gus06.file.lnk.remove.insidedir.appjar");
		findDesktopFolder = Outside.service(this,"gus06.env.windows.folder.desktop");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		File dir = (File) findDesktopFolder.g();
		return remove.f(dir);
	}
	
	public void p(Object obj) throws Exception
	{f(obj);}
}
