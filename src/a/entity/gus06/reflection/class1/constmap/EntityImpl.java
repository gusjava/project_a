package a.entity.gus06.reflection.class1.constmap;

import a.framework.*;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160425";}

	
	
	public Object t(Object obj) throws Exception
	{
		Class c = (Class) obj;
		Field[] fs = c.getDeclaredFields();
		Map map = new HashMap();
		
		for(Field f:fs) if(isTargetField(f))
		{
			String name = f.getName();
			Object value = f.get(null);
			map.put(name,value);
		}
		return map;
	}
	
	private boolean isTargetField(Field f)
	{
		int m = f.getModifiers();
		return Modifier.isStatic(m) && Modifier.isFinal(m) && Modifier.isPublic(m);
	}
}
