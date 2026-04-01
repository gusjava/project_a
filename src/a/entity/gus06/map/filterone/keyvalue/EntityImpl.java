package a.entity.gus06.map.filterone.keyvalue;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151114";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		F filter = (F) o[1];
		
		Iterator it = input.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			if(filter.f(key)) return new Object[]{key,input.get(key)};
		}
		return null;
	}
}
