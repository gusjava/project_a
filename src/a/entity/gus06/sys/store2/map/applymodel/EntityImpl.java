package a.entity.gus06.sys.store2.map.applymodel;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160313";}

	public static final String KEY_MODEL = "model";
	public static final int LIMIT = 20;
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		Map map = (Map) o[1];
		
		return applyModel(t,map,0);
	}
	
	
	
	
	
	private Map applyModel(T t, Map map, int count) throws Exception
	{
		if(count>LIMIT) throw new Exception("Recursive limit reached while applying model: "+count);
    	
		if(map.containsKey(KEY_MODEL))
		{
			String model = (String) map.get(KEY_MODEL);
			Map newMap = new HashMap();
        	
			String[] n = model.split(";");
			for(String id:n)
			{
        			Map m = getMap(t,id);
        			m = applyModel(t,m,count++);
        			newMap.putAll(m);
			}
			newMap.putAll(map);
        	
			newMap.remove(KEY_MODEL);
			return newMap;
		}
    	
		return map;
	}
	
	
	
	
	private Map getMap(T t, String id) throws Exception
	{
		Map map = (Map) t.t(id);
		if(map==null) throw new Exception("Map not found for id: "+id);
		return map;
	}
}
