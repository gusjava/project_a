package a.entity.gus06.data.viewer.class1.introspector.selector.buildkeys;

import a.framework.*;
import java.lang.reflect.*;
import java.util.ArrayList;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140820";}

	private Service filterBuilder;

	public EntityImpl() throws Exception
	{
		filterBuilder = Outside.service(this,"gus06.filter.string.simple1.one");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Class c = (Class) o[0];
		String rule = (String) o[1];
		
		ArrayList list = new ArrayList();
		
		F filter = (F) filterBuilder.t(rule);
		
		Field[] fs = c.getDeclaredFields();
		for(Field f:fs) if(isValid(f,filter)) list.add(f);
		
		Method[] ms = c.getDeclaredMethods();
		for(Method m:ms) if(isValid(m,filter)) list.add(m);
		
		return list;
	}
	
	
	
	
	private boolean isValid(Field field, F filter) throws Exception
	{return filter.f(field.getName());}
	
	private boolean isValid(Method method, F filter) throws Exception
	{return filter.f(method.getName());}
}