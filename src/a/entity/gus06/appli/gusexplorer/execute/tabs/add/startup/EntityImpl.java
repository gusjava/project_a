package a.entity.gus06.appli.gusexplorer.execute.tabs.add.startup;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20170408";}


	private Service manager;
	private Service findDesktop;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.data.manager");
		findDesktop = Outside.service(this,"gus06.env.windows.folder.startup");
	}
	
	
	public void e() throws Exception
	{
		File dir = (File) findDesktop.g();
		manager.v("add",dir);
	}
}
