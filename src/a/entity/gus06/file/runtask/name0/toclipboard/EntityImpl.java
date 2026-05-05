package a.entity.gus06.file.runtask.name0.toclipboard;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220210";}


	private Service findName0;
	private Service toClipboard;

	public EntityImpl() throws Exception
	{
		findName0 = Outside.service(this,"gus.x.file.getname0");
		toClipboard = Outside.service(this,"gus06.clipboard.access");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		
		String name0 = (String) findName0.t(file);
		toClipboard.p(name0);
		
		if(progress!=null) ((E)progress).e();
	}
}