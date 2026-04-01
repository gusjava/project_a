package a.entity.gus06.map.build.list.kvarray.ksort;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161025";}


	public Object t(Object obj) throws Exception
	{
		Map m = (Map) obj;
		List l = new ArrayList();
		
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = m.get(key);
			
			l.add(new Object[]{key,value});
		}
		Collections.sort(l,new Comparator1());
		return l;
	}
	
	
	private class Comparator1 implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			Comparable v1 = (Comparable) ((Object[]) o1)[0];
			Comparable v2 = (Comparable) ((Object[]) o2)[0];
			return v1.compareTo(v2);
		}
	}
}