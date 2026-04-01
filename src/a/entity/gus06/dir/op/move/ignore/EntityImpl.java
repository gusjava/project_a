package a.entity.gus06.dir.op.move.ignore;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170409";}


	private Service move;

	public EntityImpl() throws Exception
	{
		move = Outside.service(this,"gus06.dir.op.move");
	}


	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File in = o[0];
		File out = o[1];
		
		if(!in.isDirectory()) throw new Exception("Invalid input directory: "+in);
		if(out.exists()) return;
		
		move.p(new File[]{in,out});
	}
}