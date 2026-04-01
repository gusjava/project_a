package a.entity.gus06.file.jar.extractor2;

import java.io.File;
import java.util.Map;
import a.framework.*;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140910";}
    
	private Service extractor;
	
	public EntityImpl() throws Exception
	{
		extractor = Outside.service(this,"*gus06.file.jar.extractor2.a");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			extract((File) o[0],(Map) o[1]);
		}
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	
	private void extract(File file, Map map) throws Exception
	{
		extractor.v("jarFile",file);
		extractor.v("fileMap",map);
		
		extractor.e();
	}
}
