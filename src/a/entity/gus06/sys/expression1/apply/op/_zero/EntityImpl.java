package a.entity.gus06.sys.expression1.apply.op._zero;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Integer) return Boolean.valueOf(zero((Integer) obj));
		if(obj instanceof Double) return Boolean.valueOf(zero((Double) obj));
		if(obj instanceof Float) return Boolean.valueOf(zero((Float) obj));
		if(obj instanceof Long) return Boolean.valueOf(zero((Long) obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private boolean zero(Integer n)
	{return n.intValue()==0;}
	
	private boolean zero(Long n)
	{return n.longValue()==0;}
	
	private boolean zero(Double n)
	{return n.doubleValue()==0;}
	
	private boolean zero(Float n)
	{return n.floatValue()==0;}
}
