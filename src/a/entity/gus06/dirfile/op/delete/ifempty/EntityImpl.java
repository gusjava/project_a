package a.entity.gus06.dirfile.op.delete.ifempty;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220612";}


	private Service deleteDir;
	private Service deleteFile;


	public EntityImpl() throws Exception
	{
		deleteDir = Outside.service(this,"gus06.dir.op.delete.ifempty");
		deleteFile = Outside.service(this,"gus06.file.op.delete.ifempty");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File f = (File) obj;
		
		if(f.isDirectory()) deleteDir.p(f);
		else deleteFile.p(f);
	}
}