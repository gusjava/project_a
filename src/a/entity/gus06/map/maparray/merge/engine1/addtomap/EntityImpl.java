package a.entity.gus06.map.maparray.merge.engine1.addtomap;

import a.framework.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220903";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map0 = (Map) o[0];
		Map map = (Map) o[1];
		
		addToMap(map0,map);
	}
	
	
	private void addToMap(Map map0, Map map) throws Exception
	{	
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = get(map,key);
			Object value0 = get(map0,key);
			
			map0.put(key,merge(value0,value));
		}
	}
	
	
	private Object merge(Object value0, Object value) throws Exception
	{
		if(value0==null) return value;
		if(value==null) return value0;
		
		if(value instanceof Map && value0 instanceof Map)
		{
			Map m = new HashMap((Map) value0);
			addToMap(m,(Map) value);
			return m;
		}
		if(value instanceof List && value0 instanceof List)
		{
			List l = new ArrayList((List) value0);
			addToList(l,(List) value);
			return l;
		}
		if(value instanceof Set && value0 instanceof Set)
		{
			Set s = new HashSet((Set) value0);
			addToSet(s,(Set) value);
			return s;
		}
		
		if(value.equals(value0)) return value;
		
		List r = new ArrayList();
		r.add(value0);
		r.add(value);
		return r;
	}
	
	
	private void addToList(List list0, List list)
	{list0.addAll(list);}
	
	private void addToSet(Set set0, Set set)
	{set0.addAll(set);}
	
	private Object get(Map map, Object key)
	{return map.containsKey(key)?map.get(key):null;}
}