package a.entity.gus06.map.value.findall.min;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180408";}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		T t = (T) o[1];
		
		Map output = new HashMap();
		if(input.isEmpty()) return output;
		
		Object key0 = input.keySet().iterator().next();
		Object value0 = input.get(key0);
		
		output.put(key0,value0);
		
		Comparable minV = (Comparable) t.t(value0);
		
		Iterator it = input.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = input.get(key);
			
			Comparable v = (Comparable) t.t(value);
			int r = v.compareTo(minV);
			
			if(r<0)
			{
				minV = v;
				output.clear();
				output.put(key,value);
			}
			else if(r==0)
			{
				output.put(key,value);
			}
		}
		return output;
	}
}
