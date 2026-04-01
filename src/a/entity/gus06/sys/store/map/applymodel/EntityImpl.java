package a.entity.gus06.sys.store.map.applymodel;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140907";}

	public static final String KEY_MODEL = "model";
	public static final String KEY_MODEL_T = "model_t";
	public static final int LIMIT = 20;
	
	
	private Service getMap;
	private Service uniqueEntity;


	public EntityImpl() throws Exception
	{
		getMap = Outside.service(this,"gus06.app.inside.store");
		uniqueEntity = Outside.service(this,"entityunique");
	}
	
	
	public Object t(Object obj) throws Exception
	{return applyModel((Map) obj,0);}
	
	
	
	
	
	private Map applyModel(Map map, int count) throws Exception
	{
		if(count>LIMIT) throw new Exception("Recursive limit reached while applying model");
    	
		if(map.containsKey(KEY_MODEL_T))
		{
			String name = (String) map.get(KEY_MODEL_T);
			T trans = (T) uniqueEntity.t(name);
			Map newMap = (Map) trans.t(map);
			
			newMap.remove(KEY_MODEL_T);
			newMap.remove(KEY_MODEL);
			return newMap;
		}
    	
		if(map.containsKey(KEY_MODEL))
		{
			String model = (String) map.get(KEY_MODEL);
			Map newMap = new HashMap();
        	
			String[] n = model.split(";");
			for(String id:n)
			{
        			Map m = getMap(id);
        			m = applyModel(m,count++);
        			newMap.putAll(m);
			}
			newMap.putAll(map);
        	
			newMap.remove(KEY_MODEL);
			return newMap;
		}
    	
		return map;
	}
	
	
	
	private Map getMap(String id) throws Exception
	{
		Map map = (Map) getMap.r(id);
		if(map==null) throw new Exception("Map not found for id: "+id);
		return map;
	}
}
