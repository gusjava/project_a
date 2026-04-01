package a.entity.gus06.file.zip.perform.unzip.location;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231205";}


	private Service runUnzip;
	
	public EntityImpl() throws Exception
	{
		runUnzip = Outside.service(this,"gus06.file.zip.run.unzip.location");
	}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		File dir = (File) o[1];
		String location = (String) o[2];
		
		runUnzip.p(new Object[]{file,dir,location,null,null});
	}
}