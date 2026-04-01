package a.entity.gus06.sys.store.process.find;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, R, V {

	public String creationDate() {return "20140907";}


	private Service uniqueEntity;


	private Map map1;
	private Map map2;
	
	private Map cache;


	public EntityImpl() throws Exception
	{
		uniqueEntity = Outside.service(this,"entityunique");
		map1 = (Map) Outside.resource(this,"call.g#gus.sys.store.process.find.list1");
		map2 = (Map) Outside.resource(this,"call.g#gus.sys.store.process.find.list2");
		
		cache = new HashMap();
	}
	
	
	public Object r(String key) throws Exception
	{return find(key);}
	
	
	public Object t(Object obj) throws Exception
	{return find((String) obj);}
	
	
	public void v(String key, Object obj) throws Exception
	{cache.put(key,obj);}
	
	
	
	
	private Object find(String id) throws Exception
	{
		if(!cache.containsKey(id))
			cache.put(id,build(id));
		return cache.get(id);
	}
	
	
	
	private Object build(String id) throws Exception
	{
		String entityName = findEntityName(id);
		return uniqueEntity.t(entityName);
	}
	
	
	
	
	private String findEntityName(String id) throws Exception
	{
		if(map2.containsKey(id)) return (String) map2.get(id);
		if(map1.containsKey(id)) return (String) map1.get(id);
		throw new Exception("Unknown process id: "+id);
	}
}
