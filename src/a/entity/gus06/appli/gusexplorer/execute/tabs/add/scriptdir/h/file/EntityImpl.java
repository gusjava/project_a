package a.entity.gus06.appli.gusexplorer.execute.tabs.add.scriptdir.h.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20251212";}


	private Service manager;
	private Service dirHolder;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.data.manager");
		dirHolder = Outside.service(this,"gus06.sys.runtask2.input.file.manager");
	}
	
	
	public void e() throws Exception
	{
		File dir = (File) dirHolder.r("dir");
		manager.v("add",dir);
	}
}