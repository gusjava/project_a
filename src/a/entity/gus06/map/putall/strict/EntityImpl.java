package a.entity.gus06.map.putall.strict;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160712";}
	
	
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
			if(map.containsKey(key)) throw new Exception("Key already used inside map: "+key);
		}
		map.putAll(m);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Map m = (Map) o[1];
		
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			if(map.containsKey(key)) throw new Exception("Key already used inside map: "+key);
		}
		
		Map map1 = new HashMap(map);
		map1.putAll(m);
		
		return map1;
	}
}
