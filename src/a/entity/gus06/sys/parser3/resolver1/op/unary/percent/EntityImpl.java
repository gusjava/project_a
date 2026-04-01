package a.entity.gus06.sys.parser3.resolver1.op.unary.percent;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231020";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		Object value = t.t(cut.get(0));
		if(value==null) throw new Exception("Invalid value type for operator: null");

		if(value instanceof Double) return build((Double) value);
		if(value instanceof Integer) return build((Integer) value);
		if(value instanceof Long) return build((Long) value);
		
		throw new Exception("Invalid value type for operator: "+value.getClass().getName());
	}
	
	
	private Double build(Double value) throws Exception
	{
		return value * 0.01;
	}
	
	private Double build(Integer value) throws Exception
	{
		return value * 0.01;
	}
	
	private Double build(Long value) throws Exception
	{
		return value * 0.01;
	}
}