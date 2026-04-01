package a.entity.gus06.convert.ttoh;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160808";}


	public Object t(Object obj) throws Exception
	{return new H1((T) obj);}
	
	
	
	private class H1 implements H
	{
		private T t;
		public H1(T t) {this.t = t;}
		
		public double h(double value) throws Exception
		{
			Object r = t.t(Double.valueOf(value));
			return toDouble(r);
		}
	}
	
	
	private double toDouble(Object obj) throws Exception
	{
		if(obj instanceof Double) return ((Double) obj).doubleValue();
		if(obj instanceof String) return Double.parseDouble((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
