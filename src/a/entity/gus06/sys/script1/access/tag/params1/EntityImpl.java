package a.entity.gus06.sys.script1.access.tag.params1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150901";}
	
	public static final String K_PARAMS = "params";



	
	
	public Object t(Object obj) throws Exception
	{
		Map tag = (Map) obj;
		
		String params = (String) get(tag,K_PARAMS);
		if(params==null) throw new Exception("Params not found inside tag ["+tag+"]");
		return params;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
}
