package a.entity.gus06.find.long1.fromint;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180106";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Long) return obj;
		if(obj instanceof Integer) return Long.valueOf((Integer) obj).longValue();
		
		throw new Exception("Invalid type: "+obj.getClass().getName());
	}
}
