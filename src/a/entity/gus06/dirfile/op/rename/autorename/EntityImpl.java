package a.entity.gus06.dirfile.op.rename.autorename;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160612";}


	private Service renameDir;
	private Service renameFile;

	public EntityImpl() throws Exception
	{
		renameDir = Outside.service(this,"gus06.dir.op.rename.autorename");
		renameFile = Outside.service(this,"gus06.file.op.rename.autorename");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File f = (File) o[0];
		
		if(!f.exists()) throw new Exception("File not found: "+f);
		
		if(f.isDirectory()) renameDir.p(obj);
		else renameFile.p(obj);
	}
}
