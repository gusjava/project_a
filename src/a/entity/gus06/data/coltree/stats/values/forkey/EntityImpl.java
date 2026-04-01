package a.entity.gus06.data.coltree.stats.values.forkey;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161216";}


	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object struct = o[0];
		Object key1 = o[1];
		
		Map stats = new HashMap();
		handle(stats,key1,obj);
		return stats;
	}
	
	
	
	private void handle(Map stats, Object key1, Object obj) throws Exception
	{
		if(obj instanceof Map)		{handleMap(stats,key1,(Map) obj);return;}
		if(obj instanceof List)		{handleList(stats,key1,(List) obj);return;}
		if(obj instanceof Set)		{handleSet(stats,key1,(Set) obj);return;}
		if(obj instanceof Object[])	{handleArray(stats,key1,(Object[]) obj);return;}
	}
	
	private void handleMap(Map stats, Object key1, Map map) throws Exception
	{
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = map.get(key);
			
			if(key.equals(key1))
			increase(stats,value);
			
			handle(stats,key1,value);
		}
	}
	
	private void handleSet(Map stats, Object key1, Set set) throws Exception
	{
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Object value = it.next();
			handle(stats,key1,value);
		}
	}
	
	private void handleList(Map stats, Object key1, List list) throws Exception
	{
		for(int i=0;i<list.size();i++)
		{
			Object value = list.get(i);
			handle(stats,key1,value);
		}
	}
	
	private void handleArray(Map stats, Object key1, Object[] array) throws Exception
	{
		for(int i=0;i<array.length;i++)
		{
			Object value = array[i];
			handle(stats,key1,value);
		}
	}
	
	
	
	private void increase(Map stats, Object key)
	{
		if(!stats.containsKey(key))
		{stats.put(key,Integer.valueOf(1));return;}
		
		Integer n = (Integer) stats.get(key);
		stats.put(key,Integer.valueOf(n.intValue()+1));
	}
}
