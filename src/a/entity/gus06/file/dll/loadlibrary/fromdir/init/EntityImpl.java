package a.entity.gus06.file.dll.loadlibrary.fromdir.init;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20150607";}

	private Service loadFromDir;
	private File storeDir;
	

	public EntityImpl() throws Exception
	{
		loadFromDir = Outside.service(this,"gus06.file.dll.loadlibrary.fromdir");
		storeDir = (File) Outside.resource(this,"path#path.dlldir");
		
		loadFromDir.p(storeDir);
	}
}