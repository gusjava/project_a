package a.entity.gus06.sys.expression1.apply.op._app_inside;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170409";}
	

	private Service inside;
		
	public EntityImpl() throws Exception
	{
		inside = Outside.service(this,"inside");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String)	return inside.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
