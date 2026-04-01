package a.entity.gus06.map.build.set.kvmap;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170326";}
	
	public static final String KEY = "key";
	public static final String VALUE = "value";


	public Object t(Object obj) throws Exception
	{
		Map m = (Map) obj;
		Set s = new HashSet();
		
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = m.get(key);
			
			Map m1 = new HashMap();
			m1.put(KEY,key);
			m1.put(VALUE,value);
			
			s.add(m1);
		}
		return s;
	}
}
