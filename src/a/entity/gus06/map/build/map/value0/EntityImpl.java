package a.entity.gus06.map.build.map.value0;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161205";}


	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Collection) return build((Collection) obj);
		if(obj instanceof Object[]) return build((Object[]) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Map build(Collection c)
	{
		Map map = new HashMap();
		Iterator it = c.iterator();
		while(it.hasNext()) map.put(it.next(),"");
		return map;
	}
	
	private Map build(Object[] oo)
	{
		Map map = new HashMap();
		for(Object o:oo) map.put(o,"");
		return map;
	}
}
