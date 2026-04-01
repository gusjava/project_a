package a.entity.gus06.app.jarfile.extract1.resources2.clear;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140908";}


	private Service emptyDir;
	private File dir;
	
	public EntityImpl() throws Exception
	{
		emptyDir = Outside.service(this,"gus06.dir.perform.empty");
		dir = (File) Outside.resource(this,"path#path.dev.resourcedir2");
	}
	
	
	public void e() throws Exception
	{
		if(!dir.exists()) dir.mkdirs();
		emptyDir.p(dir);
	}
}
