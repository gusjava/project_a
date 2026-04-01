package a.entity.gus06.file.jar.extractor1;

import java.io.File;
import a.framework.*;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140721";}
    
	private Service extractor;
	
	public EntityImpl() throws Exception
	{
		extractor = Outside.service(this,"*gus06.file.jar.extractor1.a");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof File[])
		{
			File[] o = (File[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			extract(o[0],o[1],null);
		}
		else if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
			extract((File) o[0],(File) o[1],(F) o[2]);
		}
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	
	private void extract(File file, File dir, F filter) throws Exception
	{
		extractor.v("jarFile",file);
		extractor.v("outputDir",dir);
		extractor.v("filter",filter);
		
		extractor.e();
	}
}
