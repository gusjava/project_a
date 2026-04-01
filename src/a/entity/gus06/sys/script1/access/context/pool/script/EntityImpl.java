package a.entity.gus06.sys.script1.access.context.pool.script;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160323";}
	
	
	private Service isRootTag;
	private Service getCurrent;

	public EntityImpl() throws Exception
	{
		isRootTag = Outside.service(this,"gus06.sys.script1.access.tag.type1.isroot");
		getCurrent = Outside.service(this,"gus06.sys.script1.access.context.execution.current");
	}


	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		Map currentTag = (Map) getCurrent.t(context);
		
		Map stack = (Map) get1(currentTag,"stack");
		Map owner = (Map) get1(stack,"owner");
		
		while(!isRootTag.f(owner))
		{
			stack = (Map) get1(stack,"parent");
			owner = (Map) get1(stack,"owner");
		}
		
		Map pool = (Map) get1(stack,"pool");
		return pool;
	}
	
	
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
}
