package a.entity.gus06.dirfile.op.movetodir.ignore;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250730";}


	private Service moveOp;

	public EntityImpl() throws Exception
	{
		moveOp = Outside.service(this,"gus06.dirfile.op.move.ignore");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File in = (File) o[0];
		File dir = (File) o[1];
		
		File out = new File(dir,in.getName());
		moveOp.p(new File[]{in,out});
	}
}