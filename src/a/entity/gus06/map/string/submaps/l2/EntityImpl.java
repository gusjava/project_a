package a.entity.gus06.map.string.submaps.l2;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240620";}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Map map1 = new HashMap();
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) map.get(key);
			
			String[] n = key.split("\\.",2);
			if(n.length==2)
			{
				String k1 = n[0];
				String k2 = n[1];
				
				if(!map1.containsKey(k1)) map1.put(k1, new HashMap());
				Map m = (Map) map1.get(k1);
				m.put(k2, value); 
			}
		}
		return map1;
	}
}