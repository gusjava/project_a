package a.entity.gus06.sys.expression1.apply.op._parentlist;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160325";}
	
	
	
	
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
			f = f.getParentFile();
			if(f!=null) list.add(f);
		}
		return list;
	}
	
	private List parentList(Class c)
	{
		List list = new ArrayList();
		while(c!=null)
		{
			c = c.getSuperclass();
			if(c!=null) list.add(c);
		}
		return list;
	}
	
	private List parentList(Map m)
	{
		List list = new ArrayList();
		while(m!=null)
		{
			m = (Map) get(m,"parent");
			if(m!=null) list.add(m);
		}
		return list;
	}
	
	private Object get(Map m, String key)
	{return m.containsKey(key)?m.get(key):null;}
}
