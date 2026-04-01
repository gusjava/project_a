package a.entity.gus06.file.mime.tika.check.istype;

import a.framework.*;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MediaTypeRegistry;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150819";}


	private Service detect;

	public EntityImpl() throws Exception
	{
		detect = Outside.service(this,"gus06.file.mime.tika.detect.asstring");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object target = o[0];
		String type1 = (String) o[1];
		
		String type = (String) detect.t(target);
		
		return type.equals(type1);
	}
}
