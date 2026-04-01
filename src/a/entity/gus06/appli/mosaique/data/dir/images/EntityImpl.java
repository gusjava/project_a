package a.entity.gus06.appli.mosaique.data.dir.images;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20141122";}


	private Service dataDir;


	public EntityImpl() throws Exception
	{
		dataDir = Outside.service(this,"gus06.appli.mosaique.data.dir");
	}
	
	
	public Object g() throws Exception
	{
		File dir = (File) dataDir.g();
		if(dir==null || !dir.isDirectory()) return null;
		
		return dir.listFiles();
	}
}
