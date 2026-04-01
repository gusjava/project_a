package a.entity.gus06.file.runtask.clipboardfile.movefrom;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220513";}


	private Service perform;
	private Service clipboard;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.op.move.replace");
		clipboard = Outside.service(this,"gus06.clipboard.access.file");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		File file0 = (File) clipboard.g();
		if(file0!=null) perform.p(new File[]{file, file0});
		if(progress!=null) ((E)progress).e();
	}
}