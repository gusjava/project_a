package a.entity.gus06.sys.git1.find.gitroot;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200116";}


	private Service getGitFolder;

	public EntityImpl() throws Exception
	{
		getGitFolder = Outside.service(this,"gus06.sys.git1.find.gitfolder");
	}
	
	public Object t(Object obj) throws Exception
	{
		File gitFolder = (File) getGitFolder.t(obj);
		if(gitFolder==null) return null;
		return gitFolder.getParentFile();
	}
}
