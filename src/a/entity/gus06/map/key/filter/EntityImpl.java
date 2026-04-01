package a.entity.gus06.map.key.filter;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150615";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		F f = (F) o[1];
		
		if(input==null) return null;
		
		Map output = new HashMap();
		if(f!=null)
		{
			Iterator it = input.keySet().iterator();
			while(it.hasNext())
			{
				Object key = it.next();
				Object value = input.get(key);
				
				if(f.f(key)) output.put(key,value);
			}
		}
		return output;
	}
}