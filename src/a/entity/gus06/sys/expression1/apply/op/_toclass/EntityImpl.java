package a.entity.gus06.sys.expression1.apply.op._toclass;

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
		if(obj instanceof Class) return obj;
		if(obj instanceof String) return findClass.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
