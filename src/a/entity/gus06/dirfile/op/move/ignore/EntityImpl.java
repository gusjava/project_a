package a.entity.gus06.dirfile.op.move.ignore;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170409";}


	private Service moveDir;
	private Service moveFile;

	public EntityImpl() throws Exception
	{
		moveDir = Outside.service(this,"gus06.dir.op.move.ignore");
		moveFile = Outside.service(this,"gus06.file.op.move.ignore");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File in = o[0];
		File out = o[1];
		
		if(!in.exists()) throw new Exception("Input not found: "+in);
		
		if(in.isDirectory()) moveDir.p(obj);
		else moveFile.p(obj);
	}
}
