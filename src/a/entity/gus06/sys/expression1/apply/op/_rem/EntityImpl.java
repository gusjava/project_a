package a.entity.gus06.sys.expression1.apply.op._rem;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
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
		if(obj instanceof String) return new T2((String)obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private Number value;
		public T1(Number value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return value;
			return remNb(value,toNumber(obj));
		}
		
		private Object remNb(Number n1, Number n2) throws Exception
		{
			if(n1 instanceof Integer && n2 instanceof Integer)
				return Integer.valueOf(n1.intValue() - n2.intValue());
			return Double.valueOf(n1.doubleValue() - n2.doubleValue());
		}
		
		private Number toNumber(Object obj) throws Exception
		{return (Number) findNumber.t(obj);}
		
		private int toInt(Object obj)
		{return Integer.parseInt(""+obj);}
	}
	
	
	
	
	private class T2 implements T
	{
		private String value;
		public T2(String value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return value;
			return rem(value,toString_(obj));
		}
		
		private Object rem(String s1, String s2) throws Exception
		{return s1.replace(s2,"");}
		
		private String toString_(Object obj) throws Exception
		{
			if(obj instanceof Boolean) return ""+obj;
			if(obj instanceof Number) return ""+obj;
			if(obj instanceof String) return ""+obj;
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
}
