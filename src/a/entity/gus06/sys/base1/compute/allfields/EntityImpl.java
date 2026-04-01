package a.entity.gus06.sys.base1.compute.allfields;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150329";}
	
	
	public Object t(Object obj) throws Exception
	{
		G g = (G) obj;
		R r = (R) obj;
		
		Object lock = r.r("source");
		Set fields = new HashSet();
		
		synchronized(lock)
		{
			Set ids = (Set) g.g();
			Iterator it = ids.iterator();
			while(it.hasNext())
			{
				String id = (String) it.next();
				Map map = (Map) r.r("map_"+id);
				if(map!=null) fields.addAll(map.keySet());
			}
		}
		
		return fields;
	}
}
