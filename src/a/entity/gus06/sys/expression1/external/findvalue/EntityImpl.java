package a.entity.gus06.sys.expression1.external.findvalue;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150905";}
	
	public static final String KEY_PARENT = "parent";

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map pool = (Map) o[0];
		String key = (String) o[1];
		
		if(key.equals("true")) return Boolean.TRUE;
		if(key.equals("false")) return Boolean.FALSE;
		if(key.equals("null")) return null;
		
		while(!pool.containsKey(key) && pool.containsKey(KEY_PARENT))
			pool = (Map) pool.get(KEY_PARENT);
		
		return pool.containsKey(key) ? pool.get(key) : null;
	}
}
