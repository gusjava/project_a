package a.entity.gus06.map.value.fcollect;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170605";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		Map output = (Map) t(obj);
		
		input.clear();
		input.putAll(output);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		Map cMap = (Map) o[1];
		
		Map output = new HashMap();
		
		Set done = new HashSet();
		T t0 = (T) get(cMap,"*");
		cMap.remove("*");
		
		Iterator it = cMap.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			T t = (T) get(cMap,key);
			
			boolean required = key.startsWith("!");
			String field = required ? key.substring(1) : key;
			
			Object value = get(input,field);
			if(required || value!=null)
			output.put(field,t.t(value));
			
			done.add(field);
		}
		
		if(t0!=null)
		{
			it = input.keySet().iterator();
			while(it.hasNext())
			{
				String field = (String) it.next();
				Object value = input.get(field);
				
				if(!done.contains(field))
				output.put(field,t0.t(value));
			}
		}
		return output;
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
