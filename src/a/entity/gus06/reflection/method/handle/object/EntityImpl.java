package a.entity.gus06.reflection.method.handle.object;

import a.framework.*;
import java.lang.reflect.Method;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160910";}


	private Service invoke;

	public EntityImpl() throws Exception
	{
		invoke = Outside.service(this,"gus06.reflection.method.invoke.object");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		
		if(o.length==2) 
		{
			Object data = o[0];
			Method method = (Method) o[1];
			
			if(method.getReturnType().equals(Void.TYPE))
				return new E1(data,method,null);
			return invoke.t(new Object[]{data,method});
		}
		
		if(o.length==3) 
		{
			Object data = o[0];
			Method method = (Method) o[1];
			Object args = o[2];
			
			if(method.getReturnType().equals(Void.TYPE))
				return new E1(data,method,args);
			return invoke.t(new Object[]{data,method,args});
		}
		
		throw new Exception("Wrong data number: "+o.length);
	}
	
	
	private class E1 implements E
	{
		private Object data;
		private Method method;
		private Object args;
		
		public E1(Object data, Method method, Object args)
		{
			this.data = data;
			this.method = method;
			this.args = args;
		}
		
		public void e() throws Exception
		{invoke.t(new Object[]{data,method,args});}
	}
}
