package a.entity.gus06.reflection.setter.setvalue;

import a.framework.*;
import java.lang.reflect.Method;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160604";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		String name = (String) o[1];
		Object value = o[2];
		
		Method setter = findSetter(data,name);
		setter.invoke(data,new Object[]{value});
	}
	
	private Method findSetter(Object obj, String fieldName) throws Exception
	{
		String methodName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
		Method[] mm = obj.getClass().getMethods();
		for(Method m:mm) if(m.getName().equals(methodName)) return m;
		throw new Exception("Method not found: "+methodName);
	}
}
