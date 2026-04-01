package a.entity.gus06.reflection.wrap.object.change;

import a.framework.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180403";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		String rule = (String) o[1];
		Object value = o[2];
		
		Class c = data.getClass();
		
		try
		{
			Field field = c.getField(rule);
			if(!Modifier.isStatic(field.getModifiers()))
			{
				boolean accessible = field.isAccessible();
				if(!accessible) field.setAccessible(true);
				field.set(data,value);
				if(!accessible) field.setAccessible(false);
				return;
			}
		}
		catch(Exception e){}
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
