package a.entity.gus06.sys.script1.access.context.executor;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {
	
	public String creationDate() {return "20151102";}
	
	public static final String C_TAG_EXECUTOR = "tag_executor";

	
	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		P executor = (P) get(context,C_TAG_EXECUTOR);
		return executor;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
