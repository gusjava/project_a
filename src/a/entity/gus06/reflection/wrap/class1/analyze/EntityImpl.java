package a.entity.gus06.reflection.wrap.class1.analyze;

import a.framework.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160910";}


	private Service handleMethod;

	public EntityImpl() throws Exception
	{
		handleMethod = Outside.service(this,"gus06.reflection.method.handle.static1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		String rule = (String) o[1];
		
		Class c = (data instanceof Class)?(Class) data : data.getClass();
		
		try
		{
			Field field = c.getField(rule);
			if(Modifier.isStatic(field.getModifiers()))
				return field.get(null);
		}
		catch(Exception e){}
		
		Method method = findMethod(c,rule);
		if(method==null) throw new Exception("Invalid rule: "+rule);
		
		Class[] params = method.getParameterTypes();
		
		if(params.length==0) return handleMethod.t(method);
		return new T1(method,params,new ArrayList());
	}
	
	
	private class T1 implements T
	{
		private Method method;
		private Class[] params;
		private List values;
		
		public T1(Method method, Class[] params, List values)
		{
			this.method = method;
			this.params = params;
			this.values = values;
		}
		
		public Object t(Object obj) throws Exception
		{
			values.add(obj);
			if(values.size()==params.length)
				handleMethod.t(new Object[]{method,values});
			return new T1(method,params,new ArrayList(values));
		}
	}
	
	
	
	
	private Method findMethod(Class c, String rule) throws Exception
	{
		Method m1 = null;
		int nb1 = Integer.MAX_VALUE;
		
		Method[] mm = c.getMethods();
		for(Method m:mm) if(m.getName().equals(rule))
		{
			int nb = m.getParameterTypes().length;
			if(nb < nb1) {m1 = m; nb1 = nb;}
		}
		return m1;
	}
	
}
