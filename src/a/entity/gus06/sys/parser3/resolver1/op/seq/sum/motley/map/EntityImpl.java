package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.map;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170412";}



	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		Map sum = new HashMap();
		
		for(Object o:oo) handle(sum,o);
		return sum;
	}
	
	private void handle(Map map, Object o) throws Exception
	{
		if(o instanceof Map)
		{
			map.putAll((Map) o);
			return;
		}
		if(o instanceof List)
		{
			List l = (List) o;
			if(l.size()!=2) throw new Exception("Invalid sum operation between map and list");
			map.put(l.get(0),l.get(1));
			return;
		}
		if(o instanceof Object[])
		{
			Object[] l = (Object[]) o;
			if(l.length!=2) throw new Exception("Invalid sum operation between map and array");
			map.put(l[0],l[1]);
			return;
		}
	}
}
