package a.entity.gus06.dir.op.copy.each.filtered.replace;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161109";}


	private Service fileCopy;
	private Service handler;


	public EntityImpl() throws Exception
	{
		fileCopy = Outside.service(this,"gus06.file.op.copy.replace");
		handler = Outside.service(this,"gus06.dir.op.copy.handler.filtered");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File in = (File) o[0];
		File out = (File) o[1];
		F filter = (F) o[2];
		
		if(!in.isDirectory()) throw new Exception("Invalid input directory: "+in);
		if(out.isFile()) throw new Exception("Output is already a file: "+out);
		
		handler.p(new Object[]{in,out,fileCopy,filter});
	}
}
