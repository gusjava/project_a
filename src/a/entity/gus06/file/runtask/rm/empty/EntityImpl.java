package a.entity.gus06.file.runtask.rm.empty;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151017";}


	private Service empty;

	public EntityImpl() throws Exception
	{
		empty = Outside.service(this,"gus06.file.op.empty");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		empty.p(file);
		if(progress!=null) ((E)progress).e();
	}
}