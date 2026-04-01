package a.entity.gus06.map.deep.nextdata1.map;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160312";}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2)	throw new Exception("Wrong data number: "+o.length);
		
		Map data = (Map) o[0];
		String key = (String) o[1];
		
		if(!data.containsKey(key)) data.put(key,new HashMap());
		return data.get(key);
	}
}
