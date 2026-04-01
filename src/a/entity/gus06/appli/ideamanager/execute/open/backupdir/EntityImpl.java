package a.entity.gus06.appli.ideamanager.execute.open.backupdir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20151013";}


	private Service getDir;
	private Service openDir;


	public EntityImpl() throws Exception
	{
		getDir = Outside.service(this,"gus06.data.backuper.string");
		openDir = Outside.service(this,"gus06.awt.desktop.open");
	}
	
	
	public void e() throws Exception
	{
		File dir = (File) getDir.g();
		openDir.p(dir);
	}
}
