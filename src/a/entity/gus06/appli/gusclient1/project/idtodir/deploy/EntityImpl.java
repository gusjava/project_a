package a.entity.gus06.appli.gusclient1.project.idtodir.deploy;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140903";}


	public static final String DIRNAME = "deploy";
	
	private Service idToDir;
	
	
	public EntityImpl() throws Exception
	{
		idToDir = Outside.service(this,"gus06.appli.gusclient1.project.idtodir");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		File dir0 = (File) idToDir.t(obj);
		File dir1 = new File(dir0,DIRNAME);
		
		dir1.mkdirs();
		return dir1;
	}
}
