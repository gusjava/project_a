package a.entity.gus06.data.perform.random.key;

import a.framework.*;
import java.util.Map;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250706";}


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
		return random(m.keySet());
	}
	
	private Object random(Set s) throws Exception
	{return randomSet.t(s);}
}