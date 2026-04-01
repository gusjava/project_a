package a.entity.gus06.file.zip.perform.unzip;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160817";}


	private Service runUnzip;
	
	public EntityImpl() throws Exception
	{
		runUnzip = Outside.service(this,"gus06.file.zip.run.unzip");
	}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		File dir = (File) o[1];
		
		runUnzip.p(new Object[]{file,dir,null,null});
	}
}
