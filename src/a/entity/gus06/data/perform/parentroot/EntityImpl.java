package a.entity.gus06.data.perform.parentroot;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160413";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof File) return parentRoot((File) obj);
		if(obj instanceof Class) return parentRoot((Class) obj);
		if(obj instanceof Map) return parentRoot((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private File parentRoot(File f)
	{
		while(f!=null && f.getParentFile()!=null)
			f = f.getParentFile();
		return f;
	}
	
	private Class parentRoot(Class c)
	{
		while(c!=null && c.getSuperclass()!=null)
			c = c.getSuperclass();
		return c;
	}
	
	private Map parentRoot(Map m)
	{
		while(m!=null && get(m,"parent")!=null)
			m = (Map) get(m,"parent");
		return m;
	}
	
	
	private Object get(Map m, String key)
	{return m.containsKey(key)?m.get(key):null;}
}
