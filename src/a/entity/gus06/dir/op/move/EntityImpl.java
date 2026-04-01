package a.entity.gus06.dir.op.move;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150606";}


	private Service copy;
	private Service delete;

	public EntityImpl() throws Exception
	{
		copy = Outside.service(this,"gus06.dir.op.copy");
		delete = Outside.service(this,"gus06.dir.op.delete");
	}


	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File in = o[0];
		File out = o[1];
		
		if(!in.isDirectory()) throw new Exception("Invalid input directory: "+in);
		if(out.isFile()) throw new Exception("Output is already a file: "+out);
		
		
		File parent = out.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		boolean r = in.renameTo(out);
		if(r) return;
		
		copy.p(obj);
		if(!out.exists()) throw new Exception("Could not move dir "+in+" to dir "+out);
		delete.p(in);
	}
}
