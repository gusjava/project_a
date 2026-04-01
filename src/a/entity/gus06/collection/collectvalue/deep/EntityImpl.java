package a.entity.gus06.collection.collectvalue.deep;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.HashSet;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200416";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		T t = (T) o[1];
		
		return handle(data,t);
	}
	
	
	private Object handle(Object data, T t) throws Exception
	{
		if(data instanceof Map) return handleMap((Map) data,t);
		if(data instanceof Set) return handleSet((Set) data,t);
		if(data instanceof List) return handleList((List) data,t);
		if(data instanceof Object[]) return handleArray((Object[]) data,t);
		
		return t.t(data);
	}
	
	
	private Map handleMap(Map map, T t) throws Exception
	{
		Map m = new HashMap();
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = map.get(key);
			
			Object value1 = handle(value,t);
			m.put(key,value1);
		}
		return m;
	}
	
	
	private Set handleSet(Set set, T t) throws Exception
	{
		Set s = new HashSet();
		
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			Object element1 = handle(element,t);
			s.add(element1);
		}
		return s;
	}
	
	
	private List handleList(List list, T t) throws Exception
	{
		List l = new ArrayList();
		
		for(Object element : list)
		{
			Object element1 = handle(element,t);
			l.add(element1);
		}
		return l;
	}
	
	
	private Object[] handleArray(Object[] array, T t) throws Exception
	{
		Object[] a = new Object[array.length];
		for(int i=0;i<array.length;i++)
		{
			a[i] = handle(array[i],t);
		}
		return a;
	}
}
