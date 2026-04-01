package a.entity.gus06.map.build.set.kvarray;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170326";}


	public Object t(Object obj) throws Exception
	{
		Map m = (Map) obj;
		Set s = new HashSet();
		
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = m.get(key);
			
			s.add(new Object[]{key,value});
		}
		return s;
	}
}
