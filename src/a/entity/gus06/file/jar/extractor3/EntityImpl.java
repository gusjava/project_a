package a.entity.gus06.file.jar.extractor3;

import java.io.File;
import java.util.Map;
import a.framework.*;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180126";}
    
	private Service extractor;
	
	public EntityImpl() throws Exception
	{
		extractor = Outside.service(this,"*gus06.file.jar.extractor3.a");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
			extract((File) o[0],(File) o[1],(String) o[2]);
		}
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	
	private void extract(File file, File dir, String root) throws Exception
	{
		extractor.v("jarFile",file);
		extractor.v("outputDir",dir);
		extractor.v("root",root);
		
		extractor.e();
	}
}
