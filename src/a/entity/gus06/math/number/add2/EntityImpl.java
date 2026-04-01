package a.entity.gus06.math.number.add2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180112";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return add((Number) o[0], (Number) o[1]);
	}
	
	
	
	private Object add(Number n1, Number n2) throws Exception
	{
		if(n1 instanceof Integer && n2 instanceof Integer)
			return Integer.valueOf(n1.intValue() + n2.intValue());
		
		if(n1 instanceof Float && n2 instanceof Float)
			return Float.valueOf(n1.floatValue() + n2.floatValue());
		
		if(n1 instanceof Double || n2 instanceof Double)
			return Double.valueOf(n1.doubleValue() + n2.doubleValue());
		
		if(n1 instanceof Long || n2 instanceof Long)
			return Long.valueOf(n1.longValue() + n2.longValue());
		
		return Double.valueOf(n1.doubleValue() + n2.doubleValue());
	}
}