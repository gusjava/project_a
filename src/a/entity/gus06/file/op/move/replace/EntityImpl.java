package a.entity.gus06.file.op.move.replace;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150606";}


	private Service move;
	private Service delete;

	public EntityImpl() throws Exception
	{
		move = Outside.service(this,"gus06.file.op.move");
		delete = Outside.service(this,"gus.x.file.op.delete");
	}

	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File in = o[0];
		File out = o[1];
		
		if(!in.isFile()) throw new Exception("Invalid input file: "+in);
		if(out.exists()) delete.p(out);
		
		move.p(new File[]{in,out});
	}
}