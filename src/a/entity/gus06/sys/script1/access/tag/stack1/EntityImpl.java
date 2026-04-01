package a.entity.gus06.sys.script1.access.tag.stack1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {
	
	public String creationDate() {return "20160326";}
	
	public static final String K_STACK = "stack";
	

	
	public Object t(Object obj) throws Exception
	{
		Map tag = (Map) obj;
		Map stack = (Map) get(tag,K_STACK);
		if(stack==null) throw new Exception("Stack not found inside tag ["+tag+"]");
		return stack;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
