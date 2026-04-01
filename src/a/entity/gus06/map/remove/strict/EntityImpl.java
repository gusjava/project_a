package a.entity.gus06.map.remove.strict;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170419";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key = o[1];
		
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		
		map.remove(key);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key = o[1];
		
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		
		Map map1 = new HashMap(map);
		map1.remove(key);
		return map1;
	}
}
