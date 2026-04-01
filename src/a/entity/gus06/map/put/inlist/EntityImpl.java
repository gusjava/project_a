package a.entity.gus06.map.put.inlist;

import a.framework.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160613";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key = o[1];
		Object value = o[2];
		
		if(!map.containsKey(key))
			map.put(key,new ArrayList());
		
		List list = (List) map.get(key);
		list.add(value);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key = o[1];
		Object value = o[2];
		
		Map map1 = new HashMap(map);
		
		if(!map1.containsKey(key))
			map1.put(key,new ArrayList());
		
		List list = (List) map1.get(key);
		list.add(value);
		
		return map1;
	}
}
