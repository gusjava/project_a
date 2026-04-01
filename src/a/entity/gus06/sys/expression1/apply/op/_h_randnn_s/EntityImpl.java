package a.entity.gus06.sys.expression1.apply.op._h_randnn_s;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20171004";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Double) return new H1(toDouble(obj));
		if(obj instanceof Long) return new H1(toDouble(obj));
		if(obj instanceof Integer) return new H1(toDouble(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private double toDouble(Object obj)
	{return ((Number) obj).doubleValue();}
	
	
	
	private class H1 implements H
	{
		private double n;
		public H1(double n) {this.n = n;}
		
		public double h(double v) throws Exception
		{return Math.random()<0.5 ? -n : n;}
	}
}
