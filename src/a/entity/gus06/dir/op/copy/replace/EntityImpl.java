package a.entity.gus06.dir.op.copy.replace;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161109";}


	private Service perform;
	private Service delete;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.dir.op.copy");
		delete = Outside.service(this,"gus06.dir.op.delete");
	}
	
		
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File in = o[0];
		File out = o[1];
		
		if(!in.isDirectory()) throw new Exception("Invalid input directory: "+in);
		if(out.exists()) delete.p(out);
		
		perform.p(new File[]{in,out});
	}
}