package a.entity.gus06.data.perform.pop;

import a.framework.*;
import java.util.Collection;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170419";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map)		return pop((Map) obj);
		if(obj instanceof Collection)	return pop((Collection) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private Object pop(Collection c)
	{
		if(c.isEmpty()) return null;
		Object element = c.iterator().next();
		c.remove(element);
		return element;
	}
	
	private Object pop(Map m)
	{
		if(m.isEmpty()) return null;
		Object key = m.keySet().iterator().next();
		return m.remove(key);
	}
}
