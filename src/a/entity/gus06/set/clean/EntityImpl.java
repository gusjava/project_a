package a.entity.gus06.set.clean;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170201";}
	
	
	public void p(Object obj) throws Exception
	{
		Set set = (Set) obj;
		Set newSet = clean(set);
		set.clear();
		set.addAll(newSet);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Set set = (Set) obj;
		return clean(set);
	}
	
	
	private Set clean(Set set)
	{
		Set set1 = new HashSet();
		Iterator it = set.iterator();
		while(it.hasNext());
		{
			Object element = cleanElement(it.next());
			if(element!=null) set1.add(element);
		}
		return set1;
	}
	
	
	
	private Object cleanElement(Object element)
	{
		if(element==null) return null;
		if(element instanceof String)
		{
			String s = ((String) element).trim();
			return s.equals("")?null:s;
		}
		if(element instanceof Collection)
		{
			Collection c = (Collection) element;
			return c.isEmpty()?null:c;
		}
		if(element instanceof Map)
		{
			Map m = (Map) element;
			return m.isEmpty()?null:m;
		}
		return element;
	}
}
