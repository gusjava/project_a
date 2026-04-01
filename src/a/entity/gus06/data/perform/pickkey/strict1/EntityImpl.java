package a.entity.gus06.data.perform.pickkey.strict1;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170315";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map) return pick((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object pick(Map m) throws Exception
	{
		if(m.size()!=1) throw new Exception("Invalid size: "+m.size());
		return m.keySet().iterator().next();
	}
}
