package a.entity.gus06.entitydev.refactor.bin.remove;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140725";}


	private Service retrieveFile;
	private Service clearDir;
	
	public EntityImpl() throws Exception
	{
		retrieveFile = Outside.service(this,"gus06.entitydev.retrieve.classfile");
		clearDir = Outside.service(this,"gus06.dir.perform.removefiles0.clear");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File file = (File) retrieveFile.t(obj);
		File dir = file.getParentFile();
		if(dir.exists()) clearDir.p(dir);
	}
}
