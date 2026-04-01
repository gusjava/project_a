package a.entity.gus06.sys.parser3.resolver1.op.binary.powernot;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160112";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		if(cut.size()!=2) throw new Exception("Invalid split for equals operation: "+cut.size());
		
		Object o1 = t.t(cut.get(0));
		Object o2 = t.t(cut.get(1));
		
		if(o1 instanceof Set && o2 instanceof Set)
			return set_exter((Set)o1,(Set)o2);
			
		if(o1 instanceof Map && o2 instanceof Map)
			return map_exter((Map)o1,(Map)o2);
		
		throw new Exception("Invalid has operation");
	}
	
	
	private Set set_exter(Set s1, Set s2)
	{
		Set s = new HashSet();
		
		Iterator it = s1.iterator();
		while(it.hasNext())
		{
			Object el = it.next();
			if(!s2.contains(el)) s.add(el);
		}
		it = s2.iterator();
		while(it.hasNext())
		{
			Object el = it.next();
			if(!s1.contains(el)) s.add(el);
		}
		return s;
	}
	
	
	
	private Map map_exter(Map m1, Map m2)
	{
		Map m = new HashMap();
		
		Iterator it = m1.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			if(!m2.containsKey(key))
			m.put(key,m1.get(key));
		}
		it = m2.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			if(!m1.containsKey(key))
			m.put(key,m2.get(key));
		}
		return m;
	}
}
