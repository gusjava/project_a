package a.entity.gus06.sys.expression1.apply.op._minvalue;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Integer) return Integer.valueOf(Integer.MIN_VALUE);
		if(obj instanceof Double) return Double.valueOf(Double.MIN_VALUE);
		if(obj instanceof Long) return Long.valueOf(Long.MIN_VALUE);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
