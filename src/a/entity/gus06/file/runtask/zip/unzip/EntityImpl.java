package a.entity.gus06.file.runtask.zip.unzip;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150628";}


	private Service runUnzip;
	private Service getName;

	public EntityImpl() throws Exception
	{
		runUnzip = Outside.service(this,"gus06.file.zip.run.unzip");
		getName = Outside.service(this,"gus06.file.getname0");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String name = (String) getName.t(file);
		File dir = new File(file.getParentFile(),name);
		dir.mkdirs();
		
		runUnzip.p(new Object[]{file,dir,progress,interrupt});
	}
}
