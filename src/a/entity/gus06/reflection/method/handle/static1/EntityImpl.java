package a.entity.gus06.reflection.method.handle.static1;

import a.framework.*;
import java.lang.reflect.Method;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160910";}


	private Service invoke;

	public EntityImpl() throws Exception
	{
		invoke = Outside.service(this,"gus06.reflection.method.invoke.static1");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Method)
		{
			return perform((Method) obj,null);
		}
		if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			
			return perform((Method) o[0],o[1]);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private Object perform(Method method, Object args) throws Exception
	{
		if(method.getReturnType().equals(Void.TYPE))
			return new E1(method,args);
		return invoke.t(new Object[]{method,args});
	}
	
	
	private class E1 implements E
	{
		private Method method;
		private Object args;
		
		public E1(Method method, Object args)
		{
			this.method = method;
			this.args = args;
		}
		
		public void e() throws Exception
		{invoke.t(new Object[]{method,args});}
	}
}
