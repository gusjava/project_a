package a.entity.gus06.file.runtask.copy.down;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20240203";}


	private Service copyDown;

	public EntityImpl() throws Exception
	{
		copyDown = Outside.service(this,"gus06.file.perform.copydown.ask");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		copyDown.p(file);
		if(progress!=null) ((E)progress).e();
	}
}