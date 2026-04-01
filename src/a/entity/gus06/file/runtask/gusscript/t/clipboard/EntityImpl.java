package a.entity.gus06.file.runtask.gusscript.t.clipboard;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260130";}

	private Service fileToT;
	private Service clipboard;

	public EntityImpl() throws Exception
	{
		fileToT = Outside.service(this,"gus06.file.string.perform.execute.script1.filetot");
		clipboard = Outside.service(this,"gus06.clipboard.access");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		Object input = clipboard.g();
		if(input==null) return;
		
		T t = (T) fileToT.t(file);
		if(progress!=null) ((V)progress).v("size","1");
		Object output = t.t(input);
		clipboard.p(output);
		if(progress!=null) ((E)progress).e();
	}
}