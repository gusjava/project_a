package a.entity.gus06.appli.gusexplorer.execute.tabs.add.editorconfigdir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20190401";}


	private Service manager;
	private Service dirHolder;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.data.manager");
		dirHolder = Outside.service(this,"gus06.appli.gusexplorer.gui.editor.findinfos");
	}
	
	
	public void e() throws Exception
	{
		File dir = (File) dirHolder.g();
		manager.v("add",dir);
	}
}
