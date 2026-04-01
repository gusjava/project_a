package a.entity.gus06.map.clean;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170315";}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Map newMap = clean(map);
		map.clear();
		map.putAll(newMap);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		return clean(map);
	}
	
	
	private Map clean(Map map)
	{
		Map map1 = new HashMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = cleanElement(map.get(key));
			if(value!=null) map1.put(key,value);
		}
		return map1;
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
