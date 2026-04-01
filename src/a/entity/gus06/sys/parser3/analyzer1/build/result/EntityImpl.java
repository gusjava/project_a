package a.entity.gus06.sys.parser3.analyzer1.build.result;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160811";}
	
	public static final String KEY_OPERATOR = "operator";
	public static final String KEY_CONTENT = "content";

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String operator = (String) o[0];
		Object content = o[1];
		
		Map map = new HashMap();
		
		map.put(KEY_OPERATOR,operator);
		map.put(KEY_CONTENT,content);
		
		return map;
	}
}
