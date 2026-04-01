package a.entity.gus06.sys.script1.access.context.id;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170320";}
	
	public static final String C_ID = "id";

	
	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		String id = (String) get(context,C_ID);
		if(id==null) throw new Exception("Id not found inside context");
		return id;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
