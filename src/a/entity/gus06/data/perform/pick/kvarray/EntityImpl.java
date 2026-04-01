package a.entity.gus06.data.perform.pick.kvarray;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170314";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map) return pick((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object pick(Map m)
	{
		if(m.isEmpty()) return null;
		Object key = m.keySet().iterator().next();
		Object value = m.get(key);
		return new Object[]{key,value};
	}
}
