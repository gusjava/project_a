package a.entity.gus06.reflection.getter.getvalue;

import a.framework.*;
import java.lang.reflect.Method;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160604";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		String name = (String) o[1];
		
		Method getter = findGetter(data,name);
		return getter.invoke(data,(Object[]) null);
	}
	
	private Method findGetter(Object obj, String fieldName) throws Exception
	{
		String titledName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		String methodName1 = "get" + titledName;
		Method[] mm = obj.getClass().getMethods();
		for(Method m:mm) if(m.getName().equals(methodName1)) return m;
		
		String methodName2 = "is" + titledName;
		mm = obj.getClass().getMethods();
		for(Method m:mm) if(m.getName().equals(methodName2)) return m;
		
		throw new Exception("Methods not found: "+methodName1+" / "+methodName2);
	}
}
