package a.entity.gus06.appli.gusclient1.gui.direxplorer.root;

import java.io.File;
import javax.swing.JComponent;
import a.framework.*;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140723";}

	private Service explorer;
	private File dir;
	
	
	public EntityImpl() throws Exception
	{
		explorer = Outside.service(this,"*gus06.dir.explorer.simple");
		dir = (File) Outside.resource(this,"path#path.rootdir");

		if(!dir.exists()) dir.mkdirs();
		explorer.p(dir);
	}
	
	public Object i() throws Exception
	{return explorer.i();}
}
