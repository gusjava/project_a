package a.entity.gus06.sys.expression1.apply.op._new_with;

import a.framework.*;
import java.lang.reflect.Constructor;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160830";}
	
	
	
	private Service findArray;
	private Service findClass;
	
	public EntityImpl() throws Exception
	{
		findArray = Outside.service(this,"gus06.find.objectarray");
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
		public T1(Class c){this.c = c;}
		
		public Object t(Object obj) throws Exception
		{
			Object[] args = (Object[]) findArray.t(obj);
			
			Class[] argsClass = new Class[args.length];
			for(int i=0;i<args.length;i++)
			argsClass[i] = convertClass(args[i].getClass());
			
			Constructor ct = c.getConstructor(argsClass);
			return ct.newInstance(args);
		}
	}
	
	
	private Class convertClass(Class c)
	{
		if(c==Integer.class) return Integer.TYPE;
		if(c==Double.class) return Double.TYPE;
		if(c==Float.class) return Float.TYPE;
		if(c==Long.class) return Long.TYPE;
		if(c==Short.class) return Short.TYPE;
		if(c==Boolean.class) return Boolean.TYPE;
		if(c==Byte.class) return Byte.TYPE;
		if(c==Character.class) return Character.TYPE;
		
		return c;	
	}
}
