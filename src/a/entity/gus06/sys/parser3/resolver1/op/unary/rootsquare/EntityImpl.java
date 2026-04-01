package a.entity.gus06.sys.parser3.resolver1.op.unary.rootsquare;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231107";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		Object value = t.t(cut.get(0));
		if(value==null) return null;

		if(value instanceof Number) return rootsquare((Number) value);
		
		throw new Exception("Invalid value type for operator: "+value.getClass().getName());
	}
	
	private Double rootsquare(Number n)
	{
		double r = Math.cbrt(n.doubleValue());
		return Double.valueOf(r);
	}
}