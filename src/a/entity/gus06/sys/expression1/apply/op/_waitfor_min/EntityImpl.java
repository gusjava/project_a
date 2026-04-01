package a.entity.gus06.sys.expression1.apply.op._waitfor_min;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160408";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.time.execute.waitfor.min");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Long) return perform.t(obj);
		if(obj instanceof Integer) return perform.t(obj);
		if(obj instanceof String) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
