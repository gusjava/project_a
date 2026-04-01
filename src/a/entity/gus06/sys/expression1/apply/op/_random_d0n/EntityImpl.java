package a.entity.gus06.sys.expression1.apply.op._random_d0n;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20171003";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Double) return rand(toDouble(obj));
		if(obj instanceof Long) return rand(toDouble(obj));
		if(obj instanceof Integer) return rand(toDouble(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private double toDouble(Object obj)
	{return ((Number) obj).doubleValue();}
	
	
	private Double rand(double n)
	{return Double.valueOf(Math.random()*n);}
}
