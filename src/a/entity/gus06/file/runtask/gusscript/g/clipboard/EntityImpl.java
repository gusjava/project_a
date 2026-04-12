package a.entity.gus06.file.runtask.gusscript.g.clipboard;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260130";}

	private Service fileToG;
	private Service clipboard;

	public EntityImpl() throws Exception
	{
		fileToG = Outside.service(this,"gus06.file.string.perform.execute.script1.filetog");
		clipboard = Outside.service(this,"gus06.clipboard.access");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		G g = (G) fileToG.t(file);
		if(progress!=null) ((V)progress).v("size","1");
		Object output = g.g();
		clipboard.p(output);
		if(progress!=null) ((E)progress).e();
	}
}