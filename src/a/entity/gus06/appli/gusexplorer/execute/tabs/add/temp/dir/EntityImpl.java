package a.entity.gus06.appli.gusexplorer.execute.tabs.add.temp.dir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20151008";}


	private Service manager;
	private Service tempDirHolder;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.data.manager");
		tempDirHolder = Outside.service(this,"gus06.sys.clipboard1.g.listfiles.buildfile");
	}
	
	
	public void e() throws Exception
	{
		File dir = (File) tempDirHolder.g();
		manager.v("add",dir);
	}
}
