package a.entity.gus06.appli.gusclient1.tool.builder;

import java.util.HashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140814";}

	
	private Service findEntityName;
	private Service uniqueEntity;
	
	private Map cache;

	public EntityImpl() throws Exception
	{
		findEntityName = Outside.service(this,"gus06.appli.gusclient1.tool.findentity");
		uniqueEntity = Outside.service(this,"entityunique");
		cache = new HashMap();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String id = (String) obj;
		if(!cache.containsKey(id))
			cache.put(id,build(id));
		return cache.get(id);
	}
	
	
	
	private Object build(String id) throws Exception
	{
		String entityName = (String) findEntityName.t(id);
		return uniqueEntity.t(entityName);
	}
}
