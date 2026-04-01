package a.entity.gus06.map.put.rename;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20200329";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key = o[1];
		Object value = o[2];
		
		if(!map.containsKey(key))
		{map.put(key,value);return;}
		
		int k = 0;
		while(map.containsKey(key+"_"+k)) k++;
		map.put(key+"_"+k,value);
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
		{map1.put(key,value);return map1;}
		
		int k = 0;
		while(map1.containsKey(key+"_"+k)) k++;
		map1.put(key+"_"+k,value);
		
		return map1;
	}
}
