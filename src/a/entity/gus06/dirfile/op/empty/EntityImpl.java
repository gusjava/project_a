package a.entity.gus06.dirfile.op.empty;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150606";}


	private Service emptyDir;
	private Service emptyFile;


	public EntityImpl() throws Exception
	{
		emptyDir = Outside.service(this,"gus06.dir.op.empty");
		emptyFile = Outside.service(this,"gus06.file.op.empty");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File f = (File) obj;
		
		if(f.isDirectory()) emptyDir.p(f);
		else if(f.isFile()) emptyFile.p(f);
	}
}
