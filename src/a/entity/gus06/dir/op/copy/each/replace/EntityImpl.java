package a.entity.gus06.dir.op.copy.each.replace;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161109";}


	private Service fileCopy;
	private Service handler;


	public EntityImpl() throws Exception
	{
		fileCopy = Outside.service(this,"gus06.file.op.copy.replace");
		handler = Outside.service(this,"gus06.dir.op.copy.handler");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File in = o[0];
		File out = o[1];
		
		if(!in.isDirectory()) throw new Exception("Invalid input directory: "+in);
		if(out.isFile()) throw new Exception("Output is already a file: "+out);
		
		handler.p(new Object[]{in,out,fileCopy});
	}
}
