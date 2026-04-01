package a.entity.gus06.convert.rtoh;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160808";}


	public Object t(Object obj) throws Exception
	{return new H1((R) obj);}
	
	
	
	private class H1 implements H
	{
		private R r;
		public H1(R r) {this.r = r;}
		
		public double h(double value) throws Exception
		{
			Object o = r.r(""+value);
			return toDouble(o);
		}
	}
	
	
	private double toDouble(Object obj) throws Exception
	{
		if(obj instanceof Double) return ((Double) obj).doubleValue();
		if(obj instanceof String) return Double.parseDouble((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
