package a.entity.gus06.appli.gusclient1.project.idtodir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140831";}


	
	private File rootDir;
	
	
	public EntityImpl() throws Exception
	{
		rootDir = (File) Outside.resource(this,"path#path.projectdir");
		rootDir.mkdirs();
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String id = (String) obj;
		return new File(rootDir,id);
	}
}
