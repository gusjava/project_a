package a.entity.gus06.sys.expression1.apply.op._parent_l;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160327";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return new T1(obj);
		if(obj instanceof Class) return new T1(obj);
		if(obj instanceof Map) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{
			int level = toInt(obj);
			return parent(data,level);
		}
	}
	
	
	
	private Object parent(Object data, int level) throws Exception
	{
		if(data instanceof File) return parentFile((File) data,level);
		if(data instanceof Class) return parentClass((Class) data,level);
		if(data instanceof Map) return parentMap((Map) data,level);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	private File parentFile(File f, int level)
	{
		for(int i=0;i<level;i++) if(f!=null)
		f = f.getParentFile();
		return f;
	}
	
	private Class parentClass(Class c, int level)
	{
		for(int i=0;i<level;i++) if(c!=null)
		c = c.getSuperclass();
		return c;
	}
	
	private Map parentMap(Map m, int level)
	{
		for(int i=0;i<level;i++) if(m!=null)
		m = (Map) get(m,"parent");
		return m;
	}
	
	
	private Object get(Map m, String key)
	{return m.containsKey(key)?m.get(key):null;}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}
