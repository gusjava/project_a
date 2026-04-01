package a.entity.gus06.file.lnk.create.shortcut2.appjar.ondesktop;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20150607";}


	private Service createAppJarShortcut;
	private Service findDesktopFolder;
	

	public EntityImpl() throws Exception
	{
		createAppJarShortcut = Outside.service(this,"gus06.file.lnk.create.shortcut2.appjar");
		findDesktopFolder = Outside.service(this,"gus06.env.windows.folder.desktop");
	}

	public void e() throws Exception
	{
		File dir = (File) findDesktopFolder.g();
		createAppJarShortcut.p(dir);
	}
}