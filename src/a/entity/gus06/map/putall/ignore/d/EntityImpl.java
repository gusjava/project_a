package a.entity.gus06.map.putall.ignore.d;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20161218";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Map m = (Map) o[1];
		
		Map m1 = new HashMap();
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			if(!map.containsKey(key)) m1.put(key,m.get(key));
			else System.out.println("key ignored: "+key);
		}
		map.putAll(m1);
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
			if(!map1.containsKey(key)) map1.put(key,m.get(key));
			else System.out.println("key ignored: "+key);
		}
		
		return map1;
	}
}
