package a.entity.gus06.sys.expression1.apply.op._new;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151109";}
	
	
	private Service findClass;
	
	public EntityImpl() throws Exception
	{
		findClass = Outside.service(this,"gus06.find.class1");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Class) return ((Class) obj).newInstance();
		if(obj instanceof String) return toClass((String) obj).newInstance();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private Class toClass(String s) throws Exception
	{return (Class) findClass.t(s);}
}
