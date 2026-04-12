package a.entity.gus06.entitydev.entityname.packagedir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141014";}


	private File rootDir;
	
	public EntityImpl() throws Exception
	{
		rootDir = (File) Outside.resource(this,"path#path.dev.entityroot");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String name = (String) obj;
		String relPath = ("a.entity."+name).replace(".",File.separator);
		return new File(rootDir,relPath);
	}
}