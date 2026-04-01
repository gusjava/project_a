package a.entity.gus06.map.keepcol.many;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20180310";}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Collection c = (Collection) map.get(key);
			if(c.size()<2) it.remove();
		}
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Map map1 = new HashMap();
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = (String) it.next();
			Collection c = (Collection) map.get(key);
			if(c.size()>=2) map1.put(key,c);
		}
		return map1;
	}
}
