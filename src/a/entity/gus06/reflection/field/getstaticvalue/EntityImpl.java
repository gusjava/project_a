package a.entity.gus06.reflection.field.getstaticvalue;

import a.framework.*;
import java.lang.reflect.Field;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140821";}

	
	
	public Object t(Object obj) throws Exception
	{
		Field field = (Field) obj;
		
		if(field.isAccessible())
			return field.get(null);
	
		field.setAccessible(true);
		Object value = field.get(null);
		field.setAccessible(false);
		
		return value;
	}
}
