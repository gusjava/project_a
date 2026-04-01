package a.entity.gus06.app.jarfile.extract1.framework2.check;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20140801";}

	public static final int NB = 15;

	private File dir;
	
	public EntityImpl() throws Exception
	{dir = (File) Outside.resource(this,"path#path.dev.frameworkdir2");}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(!dir.exists()) return true;
		return dir.list().length!=NB;
	}
}
