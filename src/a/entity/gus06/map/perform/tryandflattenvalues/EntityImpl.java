package a.entity.gus06.map.perform.tryandflattenvalues;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200405";}

	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Map map1 = new HashMap();
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Collection value = (Collection) map.get(key);
			
			if(value.size()>1) return null;
			if(value.size()==1) map1.put(key,value.iterator().next());
		}
		return map1;
	}
}
