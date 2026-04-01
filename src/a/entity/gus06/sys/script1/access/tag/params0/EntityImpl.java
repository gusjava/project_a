package a.entity.gus06.sys.script1.access.tag.params0;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160110";}
	
	public static final String K_PARAMS = "params";



	
	
	public Object t(Object obj) throws Exception
	{
		Map tag = (Map) obj;
		return get(tag,K_PARAMS);
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
