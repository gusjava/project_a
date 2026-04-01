package a.entity.gus06.dirfile.op.copytodir.autorename;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250503";}


	private Service copyOp;


	public EntityImpl() throws Exception
	{
		copyOp = Outside.service(this,"gus06.dirfile.op.copy.autorename");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File in = (File) o[0];
		File dir = (File) o[1];
		
		File out = new File(dir,in.getName());
		copyOp.p(new File[]{in,out});
	}
}