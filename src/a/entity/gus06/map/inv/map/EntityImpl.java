package a.entity.gus06.map.inv.map;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160129";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Map map1 = new HashMap();
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = map.get(key);
			
			findSet(map1,value).add(key);
		}
		return map1;
	}
	
	
	private Set findSet(Map map, Object key)
	{
		if(!map.containsKey(key))
			map.put(key,new HashSet());
		return (Set) map.get(key);
	}
}
