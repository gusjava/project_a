package a.entity.gus06.sys.expression1.apply.op._diff;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new F1(obj);
	}
	
	
	
	private class F1 implements F
	{
		private Object value;
		public F1(Object value) {this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{
			if(value==null && obj==null) return false;
			if(value==null || obj==null) return true;
			
			if(value instanceof Number && obj instanceof Number)
				return toDouble((Number) value) != toDouble((Number) obj);
			
			return !value.equals(obj);
		}
	}
	
	
	
	private double toDouble(Number n)
	{return n.doubleValue();}
}
