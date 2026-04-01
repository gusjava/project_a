package a.entity.gus06.map.value.fcollect3;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170605";}


	private Service buildMap;
	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.map.findall3.buildmap");
	}
	
	
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
		T defaultRule = (T) get(cMap,"*");
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
			{
				Map m = (Map) buildMap.t(new Object[]{input,field});
				output.put(field,t.t(m));
			}
			done.add(field);
		}
		
		if(defaultRule!=null)
		{
			it = input.keySet().iterator();
			while(it.hasNext())
			{
				String field = (String) it.next();
				Object value = input.get(field);
				
				if(!done.contains(field))
				{
					Map m = (Map) buildMap.t(new Object[]{input,field});
					output.put(field,defaultRule.t(m));
				}
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
