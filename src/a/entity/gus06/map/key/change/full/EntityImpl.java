package a.entity.gus06.map.key.change.full;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161215";}


	private Service change;
	
	public EntityImpl() throws Exception
	{
		change = Outside.service(this,"gus06.map.key.change");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object struct = o[0];
		Object key1 = o[1];
		Object key2 = o[2];
		
		if(key1.equals(key2)) return;
		handle(struct,key1,key2);
	}
	
	
	private void handleMap(Map map, Object key1, Object key2) throws Exception
	{
		change.p(new Object[]{map,key1,key2});
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = map.get(key);
			handle(value,key1,key2);
		}
	}
	
	private void handleSet(Set set, Object key1, Object key2) throws Exception
	{
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Object value = it.next();
			handle(value,key1,key2);
		}
	}
	
	private void handleList(List list, Object key1, Object key2) throws Exception
	{
		for(int i=0;i<list.size();i++)
		{
			Object value = list.get(i);
			handle(value,key1,key2);
		}
	}
	
	private void handleArray(Object[] array, Object key1, Object key2) throws Exception
	{
		for(int i=0;i<array.length;i++)
		{
			Object value = array[i];
			handle(value,key1,key2);
		}
	}
	
	
	private void handle(Object obj, Object key1, Object key2) throws Exception
	{
		if(obj instanceof Map)		{handleMap((Map) obj,key1,key2);return;}
		if(obj instanceof List)		{handleList((List) obj,key1,key2);return;}
		if(obj instanceof Set)		{handleSet((Set) obj,key1,key2);return;}
		if(obj instanceof Object[])	{handleArray((Object[]) obj,key1,key2);return;}
	}
}
