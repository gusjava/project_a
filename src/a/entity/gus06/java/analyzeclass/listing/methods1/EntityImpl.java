package a.entity.gus06.java.analyzeclass.listing.methods1;

import a.framework.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140728";}
	
	
	public Object t(Object obj) throws Exception
	{
		Class c = toClass(obj);
		Method[] ms = c.getMethods();
		
		ArrayList list = new ArrayList();
		for(Method m:ms) if(isValid(m)) list.add(m);
		return list;
	}
	
	private Class toClass(Object obj) throws Exception
	{
		if(obj instanceof Class) return (Class) obj;
		if(obj instanceof String) return Class.forName((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private boolean isValid(Method m)
	{return !Modifier.isStatic(m.getModifiers());}
}