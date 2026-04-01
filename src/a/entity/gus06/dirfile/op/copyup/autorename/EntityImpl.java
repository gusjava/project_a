package a.entity.gus06.dirfile.op.copyup.autorename;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160715";}


	private Service copyOp;


	public EntityImpl() throws Exception
	{
		copyOp = Outside.service(this,"gus06.dirfile.op.copy.autorename");
	}

	
	public void p(Object obj) throws Exception
	{
		File in = (File) obj;
		
		File p1 = in.getParentFile();
		File p2 = p1.getParentFile();
		
		File out = new File(p2,in.getName());
		copyOp.p(new File[]{in,out});
	}
}
