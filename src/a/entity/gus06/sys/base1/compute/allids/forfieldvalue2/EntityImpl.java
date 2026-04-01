package a.entity.gus06.sys.base1.compute.allids.forfieldvalue2;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170507";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		R r = (R) o[0];
		
		String field = (String) o[1];
		String value = (String) o[2];
		
		Set ids1 = new HashSet();
		Object lock = r.r("source");
		
		synchronized(lock)
		{
			Set ids = (Set) g.g();
			Iterator it = ids.iterator();
			while(it.hasNext())
			{
				String id = (String) it.next();
				Map map = (Map) r.r("map_"+id);
				if(isTargetMap(map,field,value)) ids1.add(id);
			}
		}
		
		return ids1;
	}
	
	
	private boolean isTargetMap(Map map, String field, String seq)
	{
		if(map==null) return false;
		if(!map.containsKey(field)) return false;
		String value = (String) map.get(field);
		String[] nn = value.split(";");
		for(String n:nn) if(seq.equals(n)) return true;
		return false;
	}
}
