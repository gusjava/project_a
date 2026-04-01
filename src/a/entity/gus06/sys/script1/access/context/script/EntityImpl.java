package a.entity.gus06.sys.script1.access.context.script;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160922";}
	
	private Service getCurrent;

	public EntityImpl() throws Exception
	{
		getCurrent = Outside.service(this,"gus06.sys.script1.access.context.execution.current");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		Map currentTag = (Map) getCurrent.t(context);
		
		Map stack = (Map) get1(currentTag,"stack");
		Map script = (Map) get1(stack,"script");
		
		return script;
	}
	
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
}
