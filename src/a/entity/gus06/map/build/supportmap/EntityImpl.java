package a.entity.gus06.map.build.supportmap;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160301";}


	public Object t(Object obj) throws Exception
	{
		if(obj instanceof S && obj instanceof Map)
			return obj;
		if(obj instanceof String)
			return new SupportMap((String)obj);
		if(obj instanceof Map)
			return new SupportMap((Map)obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
