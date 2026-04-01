package a.entity.gus06.feature.op.pipe.map;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151118";}

	
	public Object t(Object obj) throws Exception
	{
		Map[] mm = (Map[]) obj;
		Map output = new HashMap();
		if(mm.length==0) return output;
		
		Iterator it = mm[0].keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = findValue(mm,key);
			if(value!=null) output.put(key,value);
		}
		return output;
	}
	
	
	private Object findValue(Map[] mm, Object o)
	{
		for(Map m:mm) o = get(m,o);
		return o;
	}
	
	private Object get(Map m, Object o)
	{
		if(o==null) return null;
		if(!m.containsKey(o)) return null;
		return m.get(o);
	}
}
