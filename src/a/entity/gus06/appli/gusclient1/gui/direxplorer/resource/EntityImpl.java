package a.entity.gus06.appli.gusclient1.gui.direxplorer.resource;

import java.io.File;
import javax.swing.JComponent;
import a.framework.*;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140730";}

	private Service explorer;
	private File dir;
	
	
	public EntityImpl() throws Exception
	{
		explorer = Outside.service(this,"*gus06.dir.explorer.resource.dir1");
		dir = (File) Outside.resource(this,"path#path.dev.resourcedir2");

		if(!dir.exists()) dir.mkdirs();
		explorer.p(dir);
	}
	
	public Object i() throws Exception
	{return explorer.i();}
}
