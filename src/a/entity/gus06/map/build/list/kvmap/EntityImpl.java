package a.entity.gus06.map.build.list.kvmap;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161025";}
	
	public static final String KEY = "key";
	public static final String VALUE = "value";


	public Object t(Object obj) throws Exception
	{
		Map m = (Map) obj;
		List l = new ArrayList();
		
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = m.get(key);
			
			Map m1 = new HashMap();
			m1.put(KEY,key);
			m1.put(VALUE,value);
			
			l.add(m1);
		}
		return l;
	}
}
