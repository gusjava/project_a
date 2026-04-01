package a.entity.gus06.reflection.method.invoke.static1;

import a.framework.*;
import java.lang.reflect.Method;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140821";}


	private Service findObjectArray;
	
	public EntityImpl() throws Exception
	{
		findObjectArray = Outside.service(this,"gus06.find.objectarray");
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
			return perform((Method) o[0], o[1]);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private Object perform(Method method, Object args) throws Exception
	{
		Object[] args1 = findArray(args);
		
		if(method.isAccessible())
			return method.invoke(null,args1);
		
		method.setAccessible(true);
		Object value = method.invoke(null,args1);
		method.setAccessible(false);
		
		return value;
	}
	
	
	
	private Object[] findArray(Object obj) throws Exception
	{
		if(obj==null) return new Object[]{};
		return (Object[]) findObjectArray.t(obj);
	}
}
