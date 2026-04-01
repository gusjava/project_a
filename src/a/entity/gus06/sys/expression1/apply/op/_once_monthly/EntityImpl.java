package a.entity.gus06.sys.expression1.apply.op._once_monthly;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190724";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.app.persister1.once.monthly");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return check(obj);
		if(obj instanceof Number) return check(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private Boolean check(Object obj) throws Exception
	{
		return Boolean.valueOf(perform.f(""+obj));
	}
}