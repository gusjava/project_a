package a.entity.gus06.java.analyzeclass.listing.fields0;

import a.framework.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140728";}
	
	
	public Object t(Object obj) throws Exception
	{
		Class c = toClass(obj);
		Field[] fs = c.getFields();
		
		ArrayList list = new ArrayList();
		for(Field f:fs) if(isValid(f)) list.add(f);
		return list;
	}
	
	private Class toClass(Object obj) throws Exception
	{
		if(obj instanceof Class) return (Class) obj;
		if(obj instanceof String) return Class.forName((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private boolean isValid(Field f)
	{return Modifier.isStatic(f.getModifiers());}
}

