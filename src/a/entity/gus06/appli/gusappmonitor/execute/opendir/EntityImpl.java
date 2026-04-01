package a.entity.gus06.appli.gusappmonitor.execute.opendir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20190414";}


	private Service manager;
	private Service open;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusappmonitor.manager");
		open = Outside.service(this,"gus06.awt.desktop.open");
	}
	
	
	public void e() throws Exception
	{
		File dir = (File) manager.r("rootDir");
		open.p(dir);
	}
}
