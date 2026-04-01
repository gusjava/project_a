package a.entity.gus06.data.perform.take.one;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180304";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof List)
			return takeOne((List) obj);
		
		if(obj instanceof Set)
			return takeOne((Set) obj);
		
		if(obj instanceof Map)
			return takeOne((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object takeOne(List list)
	{
		if(list.isEmpty()) return null;
		return list.remove(0);
	}
	
	private Object takeOne(Set set)
	{
		if(set.isEmpty()) return null;
		Object elem = set.iterator().next();
		set.remove(elem);
		return elem;
	}
	
	private Object takeOne(Map map)
	{
		if(map.isEmpty()) return null;
		Object key = map.keySet().iterator().next();
		return map.remove(key);
	}
}
