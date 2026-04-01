package a.entity.gus06.data.perform.parent;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160413";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof File) return ((File) obj).getParentFile();
		if(obj instanceof Class) return ((Class) obj).getSuperclass();
		if(obj instanceof Map) return get((Map) obj,"parent");
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object get(Map m, String key)
	{return m.containsKey(key)?m.get(key):null;}
}
