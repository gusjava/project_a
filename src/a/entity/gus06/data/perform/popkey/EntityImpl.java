package a.entity.gus06.data.perform.popkey;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170419";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map) return pop((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object pop(Map m)
	{
		if(m.isEmpty()) return null;
		Object key = m.keySet().iterator().next();
		m.remove(key);
		return key;
	}
}
