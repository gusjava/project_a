package a.entity.gus06.sys.expression1.apply.op._force;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170408";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.force");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof E) return perform.t(obj);
		if(obj instanceof F) return perform.t(obj);
		if(obj instanceof G) return perform.t(obj);
		if(obj instanceof H) return perform.t(obj);
		if(obj instanceof I) return perform.t(obj);
		if(obj instanceof P) return perform.t(obj);
		if(obj instanceof R) return perform.t(obj);
		if(obj instanceof T) return perform.t(obj);
		if(obj instanceof V) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
