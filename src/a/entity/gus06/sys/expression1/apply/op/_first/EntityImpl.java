package a.entity.gus06.sys.expression1.apply.op._first;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151109";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.first");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof List) return perform.t(obj);
		if(obj instanceof String) return perform.t(obj);
		
		if(obj instanceof Object[]) return perform.t(obj);
		if(obj instanceof boolean[]) return perform.t(obj);
		if(obj instanceof byte[]) return perform.t(obj);
		if(obj instanceof char[]) return perform.t(obj);
		if(obj instanceof int[]) return perform.t(obj);
		if(obj instanceof long[]) return perform.t(obj);
		if(obj instanceof double[]) return perform.t(obj);
		if(obj instanceof float[]) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
