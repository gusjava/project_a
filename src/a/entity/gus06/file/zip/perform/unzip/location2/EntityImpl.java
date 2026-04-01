package a.entity.gus06.file.zip.perform.unzip.location2;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231205";}


	private Service runUnzip;
	
	public EntityImpl() throws Exception
	{
		runUnzip = Outside.service(this,"gus06.file.zip.run.unzip.location2");
	}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		File dir = (File) o[1];
		String location = (String) o[2];
		Map replMap = (Map) o[3];
		
		runUnzip.p(new Object[]{file,dir,location,replMap, null,null});
	}
}
