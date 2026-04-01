package a.entity.gus06.map.intmap.build.freqmap;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150501";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map) return build((Map)obj);
		if(obj instanceof List) return build((List)obj);
		if(obj instanceof Set) return build((Set)obj);
		
		throw new Exception("Invalid input data: "+obj.getClass().getName());
	}
	
	
	private Map build(Map map)
	{
		Map m = new HashMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String value = (String) map.get(it.next());
			append(m,value);
		}
		return m;
	}
	
	
	
	private Map build(List list)
	{
		Map m = new HashMap();
		Iterator it = list.iterator();
		while(it.hasNext())
		{
			String value = (String) it.next();
			append(m,value);
		}
		return m;
	}
	
	
	private Map build(Set set)
	{
		Map m = new HashMap();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String value = (String) it.next();
			append(m,value);
		}
		return m;
	}
	
	
	
	
	private void append(Map m, String value)
	{
		if(!m.containsKey(value))
		m.put(value,Integer.valueOf(1));
		else
		{
			Integer n = (Integer) m.get(value);
			m.put(value,Integer.valueOf(n.intValue()+1));
		}
	}
}
