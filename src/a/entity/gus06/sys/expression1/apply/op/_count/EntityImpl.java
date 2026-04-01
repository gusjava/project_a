package a.entity.gus06.sys.expression1.apply.op._count;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170118";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.count");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof String)		return perform.t(value);
		if(value instanceof StringBuffer)	return perform.t(value);
		if(value instanceof StringBuilder)	return perform.t(value);
		if(value instanceof List)		return perform.t(value);
		if(value instanceof Set)		return perform.t(value);
		
		if(value instanceof Object[][])		return perform.t(value);
		if(value instanceof int[][])		return perform.t(value);
		if(value instanceof short[][])		return perform.t(value);
		if(value instanceof long[][])		return perform.t(value);
		if(value instanceof double[][])		return perform.t(value);
		if(value instanceof float[][])		return perform.t(value);
		if(value instanceof boolean[][])	return perform.t(value);
		if(value instanceof char[][])		return perform.t(value);
		
		if(value instanceof Object[])		return perform.t(value);
		if(value instanceof int[])		return perform.t(value);
		if(value instanceof short[])		return perform.t(value);
		if(value instanceof long[])		return perform.t(value);
		if(value instanceof double[])		return perform.t(value);
		if(value instanceof float[])		return perform.t(value);
		if(value instanceof boolean[])		return perform.t(value);
		if(value instanceof char[])		return perform.t(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}
