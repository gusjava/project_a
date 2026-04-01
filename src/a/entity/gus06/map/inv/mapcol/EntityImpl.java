package a.entity.gus06.map.inv.mapcol;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161207";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Map map1 = new HashMap();
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Collection value = (Collection) map.get(key);
			
			Iterator it1 = value.iterator();
			while(it1.hasNext())
			{
				Object value1 = it1.next();
				findSet(map1,value1).add(key);
			}
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
