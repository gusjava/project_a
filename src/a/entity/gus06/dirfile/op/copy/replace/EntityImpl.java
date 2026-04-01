package a.entity.gus06.dirfile.op.copy.replace;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161109";}


	private Service copyDir;
	private Service copyFile;

	public EntityImpl() throws Exception
	{
		copyDir = Outside.service(this,"gus06.dir.op.copy.replace");
		copyFile = Outside.service(this,"gus06.file.op.copy.replace");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File in = o[0];
		File out = o[1];
		
		if(!in.exists()) throw new Exception("Input not found: "+in);
		
		if(in.isDirectory()) copyDir.p(obj);
		else copyFile.p(obj);
	}
}
