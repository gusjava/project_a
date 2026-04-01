package a.entity.gus06.sys.expression1.apply.op._neg;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Integer) return Boolean.valueOf(toInt(obj)<0);
		if(obj instanceof Double) return Boolean.valueOf(toDouble(obj)<0);
		if(obj instanceof Float) return Boolean.valueOf(toFloat(obj)<0);
		if(obj instanceof Long) return Boolean.valueOf(toLong(obj)<0);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	private double toDouble(Object obj)
	{return Double.parseDouble(""+obj);}
	
	private float toFloat(Object obj)
	{return Float.parseFloat(""+obj);}
	
	private long toLong(Object obj)
	{return Long.parseLong(""+obj);}
}
