package a.entity.gus06.reflection.field.isfield;

import a.framework.*;
import java.lang.reflect.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20140810";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object holder = o[0];
		Object value = o[1];
		
		Field[] fs = holder.getClass().getDeclaredFields();
		for(Field f:fs)
		{
			boolean access = f.isAccessible();
			f.setAccessible(true);
			Object v = f.get(holder);
			boolean found = v==value;
			f.setAccessible(access);
			if(found) return true;
		}
		return false;
	}
}
