package a.entity.gus06.dir.runtask.move.down2;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20240204";}


	private Service moveDown;

	public EntityImpl() throws Exception
	{
		moveDown = Outside.service(this,"gus06.sys.filemovedown1.show");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		moveDown.p(dir);
		if(progress!=null) ((E)progress).e();
	}
}