package a.entity.gus06.map.map2tomap4;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190325";}

	
	
	public Object t(Object obj) throws Exception
	{
		Map[] o = (Map[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map a1 = o[0];
		Map a2 = o[1];
		
		Map k1 = new HashMap();
		Map k12 = new HashMap();
		Map v12 = new HashMap();
		Map k2 = new HashMap();
		
		Iterator it = a1.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = a1.get(key);
			
			if(!a2.containsKey(key)) k1.put(key,value);
			else
			{
				Object value2 = a2.get(key);
				if(Objects.equals(value,value2)) v12.put(key,value);
				else k12.put(key,new Object[]{value,value2});
			}
		}
		
		it = a2.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = a2.get(key);
			
			if(!a1.containsKey(key)) k2.put(key,value);
		}
		
		return new Map[]{k1,k12,v12,k2};
	}
}
