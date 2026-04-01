package a.entity.gus06.file.mime.tika.check.isoftype;

import a.framework.*;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MediaTypeRegistry;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150822";}


	private Service detect;

	public EntityImpl() throws Exception
	{
		detect = Outside.service(this,"gus06.file.mime.tika.detect.asmediatype");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object target = o[0];
		String type1 = (String) o[1];
		
		if(target==null) return false;
		if(target instanceof File)
		{
			File file = (File) target;
			 if(!file.isFile()) return false;
		}
		
		MediaType type = (MediaType) detect.t(target);
		MediaTypeRegistry registry = MediaTypeRegistry.getDefaultRegistry();
		
		while(type!=null)
		{
			if(type.toString().equals(type1)) return true;
			type = registry.getSupertype(type);
		}
		return false;
	}
}
