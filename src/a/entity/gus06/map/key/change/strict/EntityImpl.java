package a.entity.gus06.map.key.change.strict;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170418";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key1 = o[1];
		Object key2 = o[2];
		
		if(!map.containsKey(key1)) throw new Exception("Key not found inside map: "+key1);
		
		if(key1.equals(key2)) return;
		
		Object value = map.get(key1);
		map.remove(key1);
		map.put(key2,value);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key1 = o[1];
		Object key2 = o[2];
		
		if(!map.containsKey(key1)) throw new Exception("Key not found inside map: "+key1);
		
		Map map1 = new HashMap(map);
		if(key1.equals(key2)) return map1;
		
		Object value = map1.get(key1);
		map1.remove(key1);
		map1.put(key2,value);
		
		return map1;
	}
}
