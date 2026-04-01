package a.entity.gus06.data.coltree.stats.keys;

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
		Map stats = new HashMap();
		handle(stats,obj);
		return stats;
	}
	
	
	
	private void handle(Map stats, Object obj) throws Exception
	{
		if(obj instanceof Map)		{handleMap(stats,(Map) obj);return;}
		if(obj instanceof List)		{handleList(stats,(List) obj);return;}
		if(obj instanceof Set)		{handleSet(stats,(Set) obj);return;}
		if(obj instanceof Object[])	{handleArray(stats,(Object[]) obj);return;}
	}
	
	private void handleMap(Map stats, Map map) throws Exception
	{
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = map.get(key);
			
			increase(stats,key);
			handle(stats,value);
		}
	}
	
	private void handleSet(Map stats, Set set) throws Exception
	{
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Object value = it.next();
			handle(stats,value);
		}
	}
	
	private void handleList(Map stats, List list) throws Exception
	{
		for(int i=0;i<list.size();i++)
		{
			Object value = list.get(i);
			handle(stats,value);
		}
	}
	
	private void handleArray(Map stats, Object[] array) throws Exception
	{
		for(int i=0;i<array.length;i++)
		{
			Object value = array[i];
			handle(stats,value);
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
