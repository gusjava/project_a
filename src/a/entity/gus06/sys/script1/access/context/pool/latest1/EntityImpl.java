package a.entity.gus06.sys.script1.access.context.pool.latest1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160328";}
	
	
	private Service getLatest;

	public EntityImpl() throws Exception
	{
		getLatest = Outside.service(this,"gus06.sys.script1.access.context.pool.latest");
	}
	

	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		if(context==null) return null;
		
		Map pool = (Map) getLatest.t(context);
		Map pool1 = (Map) get1(pool,"parent");
		return pool1;
	}
	
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
}