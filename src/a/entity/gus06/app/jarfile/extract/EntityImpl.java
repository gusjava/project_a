package a.entity.gus06.app.jarfile.extract;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140721";}

    

	private Service appJar;
	private Service extractor;
	
	
	public EntityImpl() throws Exception
	{
		appJar = Outside.service(this,"gus06.app.jarfile");
		extractor = Outside.service(this,"gus06.file.jar.extractor1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof File)
		{
			File dir = (File) obj;
			
			File file = (File) appJar.g();
			extractor.p(new File[]{file,dir});
		}
		else if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			
			File dir = (File) o[0];
			F filter = (F) o[1];
			
			File file = (File) appJar.g();
			extractor.p(new Object[]{file,dir,filter});
		}
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
