package a.entity.gus06.file.mime.tika.hierarchy.description;

import a.framework.*;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MediaTypeRegistry;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150819";}


	private Service detect;

	public EntityImpl() throws Exception
	{
		detect = Outside.service(this,"gus06.file.mime.tika.detect.asmediatype");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		MediaType type = (MediaType) detect.t(obj);
		MediaTypeRegistry registry = MediaTypeRegistry.getDefaultRegistry();
		
		StringBuffer b = new StringBuffer();
		while(type!=null)
		{
			b.append(type.toString());
			type = registry.getSupertype(type);
			if(type!=null) b.append(" -> ");
		}
		return b.toString();
	}
}
