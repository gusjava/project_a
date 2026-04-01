package a.entity.gus06.map.build.sortedvalues.forkeys.strict;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170418";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		List keys = (List) o[1];
		
		return build(map,keys);
	}
	
	
	private List build(Map map, List keys) throws Exception
	{
		List values = new ArrayList();
		for(Object key:keys)
		{
			if(!map.containsKey(key))
				throw new Exception("Key not found inside map: "+key);
			values.add(map.get(key));
		}
		return values;
	}
}
