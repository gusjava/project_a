package a.entity.gus06.sys.script1.access.context.builder;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {
	
	public String creationDate() {return "20151102";}
	
	public static final String C_TAG_BUILDER = "tag_builder";

	
	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		T builder = (T) get(context,C_TAG_BUILDER);
		return builder;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
