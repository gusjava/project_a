package a.entity.gus06.sys.script1.access.tag.executor1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150903";}
	
	public static final String K_EXECUTOR = "executor";

	
	
	public Object t(Object obj) throws Exception
	{
		Map tag = (Map) obj;
			
		Object executor = get(tag,K_EXECUTOR);
		if(executor==null) throw new Exception("Executor not found inside tag ["+tag+"]");
		return executor;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
