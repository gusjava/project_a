package a.entity.gus06.file.choose.save.dir.nametofile;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140818";}


	private Service chooseDir;

	public EntityImpl() throws Exception
	{
		chooseDir = Outside.service(this,"gus06.file.choose.save.dir");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String name = (String) obj;
		File dir = (File) chooseDir.g();
		if(dir==null) return null;
		return new File(dir,name);
	}
}
