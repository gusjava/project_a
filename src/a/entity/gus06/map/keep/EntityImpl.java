package a.entity.gus06.map.keep;

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
		
		Object value = get(map,key);
		map.clear();
		if(value!=null) map.put(key,value);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key = o[1];
		
		Object value = get(map,key);
		Map map1 = new HashMap();
		if(value!=null) map1.put(key,value);
		return map1;
	}
	
	
	private Object get(Map map, Object key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
