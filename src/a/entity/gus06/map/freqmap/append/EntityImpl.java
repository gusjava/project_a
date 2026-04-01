package a.entity.gus06.map.freqmap.append;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160713";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key = o[1];
		
		append(map,key);
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key = o[1];
		
		Map map1 = new HashMap(map);
		append(map1,key);
		return map1;
	}
	
	private void append(Map map, Object key)
	{
		if(!map.containsKey(key))
		{map.put(key,Integer.valueOf(1));return;}
		
		Integer n = (Integer) map.get(key);
		map.put(key,Integer.valueOf(n.intValue()+1));
	}
}
