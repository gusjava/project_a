package a.entity.gus06.file.op.move;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140918";}


	private Service copy;
	private Service delete;

	public EntityImpl() throws Exception
	{
		copy = Outside.service(this,"gus06.file.op.copy");
		delete = Outside.service(this,"gus06.file.op.delete");
	}


	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File in = o[0];
		File out = o[1];
		
		if(!in.isFile()) throw new Exception("Invalid input file: "+in);
		if(out.exists()) throw new Exception("Output already exists: "+out);
		
		
		File parent = out.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		boolean r = in.renameTo(out);
		if(r) return;
		
		copy.p(obj);
		if(!out.exists()) throw new Exception("Could not move file "+in+" to file "+out);
		
		delete.p(in);
		if(in.exists()) throw new Exception("Could not move file "+in+" to file "+out);
	}
}
