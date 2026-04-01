package a.entity.gus06.data.perform.pop.strict1;

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
	
	
	
	
	private Object pop(Collection c) throws Exception
	{
		if(c.size()!=1) throw new Exception("Invalid size: "+c.size());
		Object element = c.iterator().next();
		c.remove(element);
		return element;
	}
	
	private Object pop(Map m) throws Exception
	{
		if(m.size()!=1) throw new Exception("Invalid size: "+m.size());
		Object key = m.keySet().iterator().next();
		return m.remove(key);
	}
}
