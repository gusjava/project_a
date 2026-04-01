package a.entity.gus06.file.lnk.create.shortcut2.appjar.onstartup;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20150607";}


	private Service createAppJarShortcut;
	private Service findStartupFolder;
	

	public EntityImpl() throws Exception
	{
		createAppJarShortcut = Outside.service(this,"gus06.file.lnk.create.shortcut2.appjar");
		findStartupFolder = Outside.service(this,"gus06.env.windows.folder.startup");
	}

	public void e() throws Exception
	{
		File dir = (File) findStartupFolder.g();
		createAppJarShortcut.p(dir);
	}
}