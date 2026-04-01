package a.entity.gus06.sys.filebackup1.perform.restore;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161018";}


	private Service retrieve;
	private Service replace;


	public EntityImpl() throws Exception
	{
		retrieve = Outside.service(this,"gus06.sys.filebackup1.perform.retrieve");
		replace = Outside.service(this,"gus06.file.op.copy.replace");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		File file = (File) o[1];
		String key = ""+o[2];
		
		File file0 = (File) retrieve.t(new Object[]{dir,key});
		if(file0==null) throw new Exception("No backup file available for backup key: "+key);
		
		replace.p(new File[]{file0,file});
	}
}
