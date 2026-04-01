package a.entity.gus06.data.perform.random.kvmap;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250706";}
	
	public static final String KEY = "key";
	public static final String VALUE = "value";


	private Service randomSet;
	
	public EntityImpl() throws Exception
	{
		randomSet = Outside.service(this,"gus06.data.perform.random.set");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map) return random((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object random(Map m) throws Exception
	{
		if(m.isEmpty()) return null;
		Object key = random(m.keySet());
		Object value = m.get(key);
		
		Map m1 = new HashMap();
		m1.put(KEY,key);
		m1.put(VALUE,value);
		return m1;
	}
	
	private Object random(Set s) throws Exception
	{return randomSet.t(s);}
}
