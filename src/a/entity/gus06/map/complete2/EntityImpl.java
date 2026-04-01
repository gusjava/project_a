package a.entity.gus06.map.complete2;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20180302";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		T t_key = (T) o[1];
		T t_value = (T) o[2];
		
		Object key = t_key.t(map);
		Object value = t_value.t(map);
		map.put(key,value);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		T t_key = (T) o[1];
		T t_value = (T) o[2];
		
		Map map1 = new HashMap(map);
		
		Object key = t_key.t(map1);
		Object value = t_value.t(map1);
		map1.put(key,value);
		
		return map1;
	}
}
