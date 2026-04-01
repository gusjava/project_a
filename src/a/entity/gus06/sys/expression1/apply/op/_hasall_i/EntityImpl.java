package a.entity.gus06.sys.expression1.apply.op._hasall_i;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221008";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.hasall.i");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return perform.t(value);
		
		if(value instanceof List) return perform.t(value);
		if(value instanceof Set) return perform.t(value);
		if(value instanceof Map) return perform.t(value);
		if(value instanceof Object[]) return perform.t(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}