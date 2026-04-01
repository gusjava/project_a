package a.entity.gus06.map.putall.inlist;

import a.framework.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160821";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Map m = (Map) o[1];
		
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Collection c = (Collection) m.get(key);
			
			if(!map.containsKey(key))
				map.put(key,new ArrayList());
			List list = (List) map.get(key);
			list.addAll(c);
		}
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Map m = (Map) o[1];
		
		Map map1 = new HashMap(map);
		
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Collection c = (Collection) m.get(key);
			
			if(!map1.containsKey(key))
				map1.put(key,new ArrayList());
			List list = (List) map1.get(key);
			list.addAll(c);
		}
		return map1;
	}
}
