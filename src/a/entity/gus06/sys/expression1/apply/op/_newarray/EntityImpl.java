package a.entity.gus06.sys.expression1.apply.op._newarray;

import a.framework.*;
import java.lang.reflect.Array;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170314";}
	
	
	
	private Service findClass;

	public EntityImpl() throws Exception
	{
		findClass = Outside.service(this,"gus06.find.class1");
	}	
	

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Class) return new T1((Class) obj);
		if(obj instanceof String) return new T1((Class) findClass.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Class c;
		public T1(Class c) {this.c = c;}
		
		public Object t(Object obj) throws Exception
		{
			int n = toInt(obj);
			return Array.newInstance(c,n);
		}
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
}
