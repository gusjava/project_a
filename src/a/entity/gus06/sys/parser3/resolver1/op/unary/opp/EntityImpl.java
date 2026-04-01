package a.entity.gus06.sys.parser3.resolver1.op.unary.opp;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.math.BigDecimal;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151029";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.opp");
	}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		Object value = t.t(cut.get(0));
		if(value==null) return null;
		
		if(value instanceof Set)
			return perform.t(value);
		if(value instanceof List)
			return perform.t(value);
		if(value instanceof H)
			return perform.t(value);

		if(value instanceof Integer)
			return perform.t(value);
		if(value instanceof Double)
			return perform.t(value);
		if(value instanceof Float)
			return perform.t(value);
		if(value instanceof Long)
			return perform.t(value);
		if(value instanceof BigDecimal)
			return perform.t(value);
		
		if(value instanceof int[])
			return perform.t(value);
		if(value instanceof double[])
			return perform.t(value);
		if(value instanceof float[])
			return perform.t(value);
		if(value instanceof long[])
			return perform.t(value);
		
		throw new Exception("Invalid value type for operator: "+value.getClass().getName());
	}
}
