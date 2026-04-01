package a.entity.gus06.map.build.list.kvmap.vsort;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161025";}
	
	public static final String KEY = "key";
	public static final String VALUE = "value";


	public Object t(Object obj) throws Exception
	{
		Map m = (Map) obj;
		List l = new ArrayList();
		
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = m.get(key);
			
			if(value==null) throw new Exception("Null value found for key="+key);
			
			Map m1 = new HashMap();
			m1.put(KEY,key);
			m1.put(VALUE,value);
			
			l.add(m1);
		}
		Collections.sort(l,new Comparator1());
		return l;
	}
	
	
	private class Comparator1 implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			Object v1 = ((Map) o1).get(VALUE);
			Object v2 = ((Map) o2).get(VALUE);
			
			if(v1 instanceof Comparable && v2 instanceof Comparable)
			{
				return ((Comparable)v1).compareTo((Comparable) v2);
			}
			if(v1 instanceof Collection && v2 instanceof Collection)
			{
				Integer size1 = ((Collection)v1).size();
				Integer size2 = ((Collection)v2).size();
				return size1.compareTo(size2);
			}
			throw new RuntimeException("Failed to compare values: "+v1.getClass()+" and "+v2.getClass());
		}
	}
}
