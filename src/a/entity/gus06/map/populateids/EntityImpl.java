package a.entity.gus06.map.populateids;

import a.framework.*;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160229";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		Map config = (Map) o[1];
		
		Iterator it = input.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			populateMap((Map)input.get(key),config);
		}
	}
	
	
	
	private void populateMap(Map map, Map config)
	{	
		Iterator it = config.keySet().iterator();
		while(it.hasNext())
		{
			String key0 = (String) it.next();
			Map map0 = (Map) config.get(key0);
			populateMap(map,key0,map0);
		}
	}
	
	
	
	private void populateMap(Map map, String key0, Map map0)
	{
		String key1 = key0+"_id";
		if(!map.containsKey(key1)) return;
		
		Object id = map.get(key1);
		if(!map0.containsKey(id)) return;
		
		map.put(key0,map0.get(id));
	}
}
