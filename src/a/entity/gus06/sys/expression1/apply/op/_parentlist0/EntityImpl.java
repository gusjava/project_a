package a.entity.gus06.sys.expression1.apply.op._parentlist0;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160327";}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return parentList((File) obj);
		if(obj instanceof Class) return parentList((Class) obj);
		if(obj instanceof Map) return parentList((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private List parentList(File f)
	{
		List list = new ArrayList();
		while(f!=null)
		{
			list.add(f);
			f = f.getParentFile();
		}
		return list;
	}
	
	private List parentList(Class c)
	{
		List list = new ArrayList();
		while(c!=null)
		{
			list.add(c);
			c = c.getSuperclass();
		}
		return list;
	}
	
	private List parentList(Map m)
	{
		List list = new ArrayList();
		while(m!=null)
		{
			list.add(m);
			m = (Map) get(m,"parent");
		}
		return list;
	}
	
	private Object get(Map m, String key)
	{return m.containsKey(key)?m.get(key):null;}
}
