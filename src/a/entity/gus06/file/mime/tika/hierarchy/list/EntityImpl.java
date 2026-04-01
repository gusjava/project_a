package a.entity.gus06.file.mime.tika.hierarchy.list;

import a.framework.*;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MediaTypeRegistry;
import java.util.ArrayList;

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
		
		ArrayList list = new ArrayList();
		while(type!=null)
		{
			list.add(type.toString());
			type = registry.getSupertype(type);
		}
		return list;
	}
}
