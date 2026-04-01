package a.entity.gus06.sys.expression1.apply.op._mult;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151205";}
	
	private Service findNumber;
	
	public EntityImpl() throws Exception
	{findNumber = Outside.service(this,"gus06.find.number");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Number) return new T1((Number)obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Number value;
		public T1(Number value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return mult(value,toNumber(obj));}
	}
	
	
	private Object mult(Number n1, Number n2) throws Exception
	{
		if(n1 instanceof Integer && n2 instanceof Integer)
			return Integer.valueOf(n1.intValue() * n2.intValue());
		return Double.valueOf(n1.doubleValue() * n2.doubleValue());
	}
	
	
	private Number toNumber(Object obj) throws Exception
	{return (Number) findNumber.t(obj);}
}
