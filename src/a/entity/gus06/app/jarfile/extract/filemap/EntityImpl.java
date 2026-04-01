package a.entity.gus06.app.jarfile.extract.filemap;

import a.framework.*;
import java.io.File;
import java.util.Map;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140910";}

    

	private Service appJar;
	private Service extractor;
	
	
	public EntityImpl() throws Exception
	{
		appJar = Outside.service(this,"gus06.app.jarfile");
		extractor = Outside.service(this,"gus06.file.jar.extractor2");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof Map)
		{
			Map map = (Map) obj;
			File file = (File) appJar.g();
			extractor.p(new Object[]{file,map});
		}
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
