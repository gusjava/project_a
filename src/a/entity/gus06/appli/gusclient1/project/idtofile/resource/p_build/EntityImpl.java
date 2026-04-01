package a.entity.gus06.appli.gusclient1.project.idtofile.resource.p_build;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140907";}


	public static final String FILENAME = "p_build";
	
	private Service idToDir;
	
	
	public EntityImpl() throws Exception
	{
		idToDir = Outside.service(this,"gus06.appli.gusclient1.project.idtodir.resource");
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
