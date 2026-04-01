package a.entity.gus06.map.build.sortedvalues.forkeys;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161020";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		List keys = (List) o[1];
		
		return build(map,keys);
	}
	
	
	private List build(Map map, List keys)
	{
		List values = new ArrayList();
		for(Object key:keys)
		{
			if(map.containsKey(key))
			values.add(map.get(key));
		}
		return values;
	}
}
