package a.entity.gus06.map.putall.instring;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20161205";}
	
	
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
			String s0 = (String) m.get(key);
			
			if(!map.containsKey(key))
				map.put(key,"");
			String s = (String) map.get(key);
			map.put(key,s+s0);
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
			String s0 = (String) m.get(key);
			
			if(!map1.containsKey(key))
				map1.put(key,"");
			String s = (String) map1.get(key);
			map.put(key,s+s0);
		}
		return map1;
	}
}
