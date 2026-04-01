package a.entity.gus06.sys.listtabviewer1.data.buildmap.frommapper;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200405";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List data = (List) o[0];
		T mapper = (T) o[1];
		
		Map map = new HashMap();
		for(Object item : data)
		{
			Map m = (Map) item;
			Object value = mapper.t(m);
			if(value==null) throw new Exception("Failed to map key for map: "+m);
			
			if(!map.containsKey(value))
				map.put(value,new ArrayList());
			((List) map.get(value)).add(item);
		}
		
		return map;
	}
}
