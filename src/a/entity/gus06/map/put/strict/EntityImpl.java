package a.entity.gus06.map.put.strict;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160712";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key = o[1];
		Object value = o[2];
		
		if(map.containsKey(key))
			throw new Exception("Key already used inside map: "+key);
			
		map.put(key,value);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key = o[1];
		Object value = o[2];
		
		if(map.containsKey(key))
			throw new Exception("Key already used inside map: "+key);
		
		Map map1 = new HashMap(map);
		map1.put(key,value);
		
		return map1;
	}
}
