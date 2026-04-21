package a.entity.gus.x.json.makeserialisable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260420";}

	public EntityImpl() throws Exception {
		
	}
	
	public Object t(Object obj) throws Exception
	{
		return handle(obj);
	}
	
	private Object handle(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof List) return handleList((List) obj);
		if(obj instanceof Map) return handleMap((Map) obj);
		if(obj instanceof Entity) return handleEntity((Entity) obj);
		if(obj instanceof Object[]) return handleArray((Object[]) obj);
		return obj.toString();
	}
	
	private List handleList(List list) throws Exception
	{
		List result = new ArrayList();
		for(int i=0;i<list.size();i++)
			result.add(handle(list.get(i)));
		return result;
	}
	
	private Map handleMap(Map map) throws Exception
	{
		Map result = new LinkedHashMap();
		for(Object key : map.keySet())
			result.put(key, handle(map.get(key)));
		return result;
	}
	
	private String handleEntity(Entity entity) throws Exception
	{
		String pkg = entity.getClass().getPackage().getName();
		if(!pkg.startsWith("a.entity.")) throw new Exception("Invalid entity package: "+pkg);
		return pkg.substring("a.entity.".length());
	}
	
	private List handleArray(Object[] array) throws Exception
	{
		List result = new ArrayList();
		for(int i=0;i<array.length;i++)
			result.add(handle(array[i]));
		return result;
	}
}
