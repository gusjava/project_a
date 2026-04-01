package a.entity.gus06.sys.script1.access.context.execution;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170220";}
	
	public static final String C_EXECUTION = "execution";

	
	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		Map execution = (Map) get(context,C_EXECUTION);
		if(execution==null) throw new Exception("Execution map not found inside context");
		return execution;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
