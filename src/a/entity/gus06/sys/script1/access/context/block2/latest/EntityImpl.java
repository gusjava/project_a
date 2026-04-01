package a.entity.gus06.sys.script1.access.context.block2.latest;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160202";}
	
	
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
		Map block2 = (Map) get1(stack,"block2");
		
		return block2;
	}
	
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
}