package a.entity.gus06.sys.script1.access.context.alias1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150903";}
	
	public static final String C_ALIAS = "alias";

	
	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		Map alias = (Map) get(context,C_ALIAS);
		if(alias==null) throw new Exception("Alias map not found inside context");
		return alias;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
