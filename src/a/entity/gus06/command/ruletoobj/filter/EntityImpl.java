package a.entity.gus06.command.ruletoobj.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140703";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object element = o[0];
		String search = (String) o[1];
		
		if(element instanceof Map)
			return filterMap((Map) element,search);
		
		if(element instanceof List)
			return filterList((List) element,search);
		
		if(element instanceof List)
			return filterList((List) element,search);
		
		if(element instanceof Set)
			return filterSet((Set) element,search);
		
		return element;
	}
	
	
	
	private Map filterMap(Map map, String search)
	{
		Map map1 = new HashMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			if(key.toString().toLowerCase().contains(search))
				map1.put(key,map.get(key));
		}
		return map1;
	}
	
	
	
	private List filterList(List list, String search)
	{
		List list1 = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			Object key = list.get(i);
			if(key.toString().toLowerCase().contains(search))
				list1.add(key);
		}
		return list1;
	}
	
	
	
	private Set filterSet(Set set, String search)
	{
		Set set1 = new HashSet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			if(key.toString().toLowerCase().contains(search))
				set1.add(key);
		}
		return set1;
	}
	
	
}
