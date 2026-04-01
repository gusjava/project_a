package a.entity.gus06.tostring.size;

import a.framework.*;
import java.util.Map;
import java.util.Collection;
import java.io.File;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140828";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Object[])
			return ""+((Object[]) obj).length;
		if(obj instanceof File[])
			return ""+((File[]) obj).length;
		if(obj instanceof String[])
			return ""+((String[]) obj).length;
		if(obj instanceof byte[])
			return ""+((byte[]) obj).length;
			
		if(obj instanceof Map)
			return ""+((Map) obj).size();
		if(obj instanceof Collection)
			return ""+((Collection) obj).size();
		if(obj instanceof String)
			return ""+((String) obj).length();
			
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
