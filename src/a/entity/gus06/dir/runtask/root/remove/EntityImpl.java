package a.entity.gus06.dir.runtask.root.remove;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150604";}


	private Service empty;
	
	public EntityImpl() throws Exception
	{
		empty = Outside.service(this,"gus06.dir.runtask.root.empty");
	}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		empty.p(obj);
		
		if(interrupt!=null && !interrupt.isEmpty()) return;
		delete(dir);
	}
	
	
	
	private void delete(File f) throws Exception
	{
		boolean r = f.delete();
		if(!r) throw new Exception("Failed to delete file: "+f);
	}
}
