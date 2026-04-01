package a.entity.gus06.appli.gusclient1.project.idtofile.deploy.jar;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150312";}


	public static final String FILENAME = "app.jar";

	private Service idToDir;


	public EntityImpl() throws Exception
	{
		idToDir = Outside.service(this,"gus06.appli.gusclient1.project.idtodir.deploy");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		File dir = (File) idToDir.t(obj);
		File file = new File(dir,FILENAME);
		
		file.createNewFile();
		return file;
	}
}
