package a.entity.gus06.dir.runtask.rename.name0.guirenamer1;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250210";}

	private Service handle;
	
	public EntityImpl() throws Exception
	{
		handle = Outside.service(this,"gus06.sys.filesrenamer1.name0.show.inframe");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		handle.p(dir);
	}
}