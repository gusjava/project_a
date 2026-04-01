package a.entity.gus06.map.string.resolve1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160324";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		String delim = (String) o[1];
		
		if(delim.length()!=2) throw new Exception("Invalid delim info: "+delim);
		char d1 = delim.charAt(0);
		char d2 = delim.charAt(1);
		
		
		Map map1 = new HashMap();
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) map.get(key);
			String value1 = buildValue(map,value,d1,d2);
			
			map1.put(key,value1);
		}
		return map1;
	}
	
	
	
	private String buildValue(Map map, String value, char d1, char d2) throws Exception
	{
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String k = (String) it.next();
			String v = (String) map.get(k);
			
			value = value.replace(d1+k+d2,v);
		}
		return value;
	}
}
