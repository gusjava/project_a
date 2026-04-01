package a.entity.gus06.map.build.invkeyvalue;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150722";}


	public Object t(Object obj) throws Exception
	{
		Map m = (Map) obj;
		Map r = new HashMap();
		
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = m.get(key);
			
			r.put(value,key);
		}
		return r;
	}
}
