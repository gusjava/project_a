package a.entity.gus06.sys.filebackup1.perform.save;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161018";}


	private Service getNow;
	private Service copy;


	public EntityImpl() throws Exception
	{
		getNow = Outside.service(this,"gus06.time.now");
		copy = Outside.service(this,"gus.x.file.op.copy");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = o[0];
		File file = o[1];
		
		String now = (String) getNow.g();
		String name = file.getName();
		
		File file1 = new File(dir,now+"_"+name);
		
		copy.p(new File[]{file,file1});
	}
}
