package a.entity.gus06.reflection.method.invoke.object;

import a.framework.*;
import java.lang.reflect.Method;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160910";}


	private Service findObjectArray;
	
	public EntityImpl() throws Exception
	{
		findObjectArray = Outside.service(this,"gus06.find.objectarray");
	}


	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		
		if(o.length==2) 
		{
			return perform(o[0],(Method) o[1],null);
		}
		if(o.length==3) 
		{
			return perform(o[0], (Method) o[1], o[2]);
		}
		throw new Exception("Wrong data number: "+o.length);
	}
	
	
	
	private Object perform(Object data, Method method, Object args) throws Exception
	{
		Object[] args1 = findArray(args);
		
		if(method.isAccessible())
			return method.invoke(data,args1);
		
		method.setAccessible(true);
		Object value = method.invoke(data,args1);
		method.setAccessible(false);
		
		return value;
	}
	
	
	
	private Object[] findArray(Object obj) throws Exception
	{
		if(obj==null) return new Object[]{};
		return (Object[]) findObjectArray.t(obj);
	}
}
