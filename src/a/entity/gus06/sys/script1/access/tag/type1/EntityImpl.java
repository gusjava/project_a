package a.entity.gus06.sys.script1.access.tag.type1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150903";}
	
	public static final String K_TYPE = "type";


	
	
	public Object t(Object obj) throws Exception
	{
		Map tag = (Map) obj;
			
		String type = (String) get(tag,K_TYPE);
		if(type==null) throw new Exception("Type not found inside tag ["+tag+"]");
		return type;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
