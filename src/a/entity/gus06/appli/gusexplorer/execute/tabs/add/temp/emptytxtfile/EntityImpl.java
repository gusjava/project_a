package a.entity.gus06.appli.gusexplorer.execute.tabs.add.temp.emptytxtfile;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20151008";}
	
	public static final String EXTENSION = "txt";


	private Service manager;
	private Service buildFile;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.data.manager");
		buildFile = Outside.service(this,"gus06.sys.clipboard1.g.listfiles.buildfile");
	}
	
	
	public void e() throws Exception
	{
		File file = (File) buildFile.t(EXTENSION);
		file.createNewFile();
		manager.v("add",file);
	}
}
