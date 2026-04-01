package a.entity.gus06.set.count.total;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161214";}

	
	
	public Object t(Object obj) throws Exception
	{
		int count = countSet((Set) obj);
		return Integer.valueOf(count);
	}
	
	
	
	private int count(Object obj)
	{
		if(obj instanceof Map) return countMap((Map) obj);
		if(obj instanceof List) return countList((List) obj);
		if(obj instanceof Set) return countSet((Set) obj);
		if(obj instanceof Object[]) return countArray((Object[]) obj);
		
		return 1;
	}
	
	
	private int countMap(Map map)
	{
		int count = 0;
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = map.get(key);
			count += count(value);
		}
		return count;
	}
	
	private int countSet(Set set)
	{
		int count = 0;
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Object value = it.next();
			count += count(value);
		}
		return count;
	}
	
	private int countList(List list)
	{
		int count = 0;
		for(int i=0;i<list.size();i++)
		{
			Object value = list.get(i);
			count += count(value);
		}
		return count;
	}
	
	private int countArray(Object[] array)
	{
		int count = 0;
		for(int i=0;i<array.length;i++)
		{
			Object value = array[i];
			count += count(value);
		}
		return count;
	}
	
	
}
