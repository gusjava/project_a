package a.entity.gus06.map.build.list.kvarray;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160713";}


	public Object t(Object obj) throws Exception
	{
		Map m = (Map) obj;
		List l = new ArrayList();
		
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = m.get(key);
			
			l.add(new Object[]{key,value});
		}
		return l;
	}
}
