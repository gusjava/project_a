package a.entity.gus06.filter.object.build.equals;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160817";}

	
	public Object t(Object obj) throws Exception
	{
		return new Filter(obj);
	}
	
	
	private class Filter implements F
	{
		private Object value;
		public Filter(Object value)
		{this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{
			if(value==null && obj==null) return true;
			if(value==null || obj==null) return false;
			
			if(value instanceof Number && obj instanceof Number)
				return toDouble((Number) value) == toDouble((Number) obj);
			
			return value.equals(obj);
		}
	}
	
	private double toDouble(Number n)
	{return n.doubleValue();}
}
