package a.entity.gus06.sys.script1.access.context.output0;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150903";}
	
	public static final String C_OUTPUT = "output";

	
	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		P output = (P) get(context,C_OUTPUT);
		return output;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
