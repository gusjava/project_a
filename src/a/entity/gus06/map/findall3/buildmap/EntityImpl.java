package a.entity.gus06.map.findall3.buildmap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170326";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		Object key = o[1];
		Object value = input.get(key);
			
		Map m = new HashMap();
		
		m.put("map",input);
		m.put("key",key);
		m.put("value",value);
		m.put("keyset",new HashSet(input.keySet()));
		m.put("valueset",new HashSet(input.values()));
		m.put("others",others(input,key));
		m.put("size",Integer.valueOf(input.size()));
			
		return m;
	}
	
	
	private Map others(Map map, Object key)
	{
		Map map1 = new HashMap(map);
		map1.remove(key);
		return map1;
	}
}
