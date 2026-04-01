package a.entity.gus06.sys.script1.access.tag.stack1.pool1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160328";}
	
	public static final String S_POOL = "pool";


	private Service getStack;

	public EntityImpl() throws Exception
	{
		getStack = Outside.service(this,"gus06.sys.script1.access.tag.stack1");
	}

	
	public Object t(Object obj) throws Exception
	{
		Map tag = (Map) obj;
		Map stack = (Map) getStack.t(tag);
		Map pool = (Map) get(stack,S_POOL);
		
		if(pool==null) throw new Exception("Pool not found for tag ["+tag+"]");
		return pool;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
