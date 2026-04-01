package a.entity.gus06.dir.runtask.zipcontent;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231126";}
	


	private Service runZip;
	
	public EntityImpl() throws Exception
	{runZip = Outside.service(this,"gus06.file.zip.run.zip");}
	

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File zip = new File(dir.getAbsolutePath()+".zip");
		
		File[] ff = dir.listFiles();
		runZip.p(new Object[]{ff,zip,progress,interrupt});
	}
}