package a.entity.gus06.service.extract.target;

import a.framework.*;
import java.lang.reflect.Field;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201129";}
	
	
	public Object t(Object obj) throws Exception
	{
		Service s = (Service) obj;
		
		Field[] fs = s.getClass().getDeclaredFields();
		for(Field f : fs)
		if(f.getName().equals("target"))
		{
			f.setAccessible(true);
			Object target = f.get(s);
			f.setAccessible(false);
			return target;
		}
		return null;
	}
}