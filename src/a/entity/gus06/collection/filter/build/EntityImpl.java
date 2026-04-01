package a.entity.gus06.collection.filter.build;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151101";}
	
	
	public Object t(Object obj) throws Exception
	{return new T1((F) obj);}
	
	
	
	private class T1 implements T
	{
		private F f;
		public T1(F f) {this.f = f;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof Set) return filterSet((Set) obj,f);
			if(obj instanceof Map) return filterMap((Map) obj,f);
			if(obj instanceof List) return filterList((List) obj,f);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	
	private Set filterSet(Set set, F f) throws Exception
	{
		Set output = new HashSet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Object elem = it.next();
			if(f.f(elem)) output.add(elem);
		}
		return output;
	}
	
	private Map filterMap(Map map, F f) throws Exception
	{
		Map output = new HashMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			if(f.f(key)) output.put(key,map.get(key));
		}
		return output;
	}
	
	private List filterList(List list, F f) throws Exception
	{
		List output = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			Object elem = list.get(i);
			if(f.f(elem)) output.add(elem);
		}
		return output;
	}
}
