package a.entity.gus06.dirfile.op.rename;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160611";}


	private Service renameDir;
	private Service renameFile;
	private Service existsIC;


	public EntityImpl() throws Exception
	{
		renameDir = Outside.service(this,"gus06.dir.op.rename");
		renameFile = Outside.service(this,"gus06.file.op.rename");
		existsIC = Outside.service(this,"gus06.file.filter.exists.ignorecase");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File f = (File) o[0];
		
		if(!existsIC.f(f)) throw new Exception("File not found: "+f);
		
		if(f.isDirectory()) renameDir.p(obj);
		else renameFile.p(obj);
	}
}
