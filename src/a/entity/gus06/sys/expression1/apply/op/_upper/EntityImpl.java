package a.entity.gus06.sys.expression1.apply.op._upper;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151109";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.upper");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Set) return perform.t(obj);
		if(obj instanceof List) return perform.t(obj);
		if(obj instanceof Map) return perform.t(obj);
		
		if(obj instanceof Object[][]) return perform.t(obj);
		if(obj instanceof Object[]) return perform.t(obj);
		
		if(obj instanceof String) return perform.t(obj);
		if(obj instanceof Number) perform.t(obj);
		if(obj instanceof Boolean) perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
