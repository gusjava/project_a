package a.entity.gus06.file.lnk.check.insidedir.appjar.ondesktop;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20180309";}


	private Service check;
	private Service findDesktopFolder;

	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.file.lnk.check.insidedir.appjar");
		findDesktopFolder = Outside.service(this,"gus06.env.windows.folder.desktop");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		File dir = (File) findDesktopFolder.g();
		return check.f(dir);
	}
}
