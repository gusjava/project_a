package a.entity.gus06.convert.ttof;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160808";}


	public Object t(Object obj) throws Exception
	{return new F1((T) obj);}
	
		
	
	private class F1 implements F
	{
		private T t;
		public F1(T t) {this.t = t;}
		
		public boolean f(Object obj) throws Exception
		{
			Object r = t.t(obj);
			return toBoolean(r);
		}
	}
	
	
	private boolean toBoolean(Object obj) throws Exception
	{
		if(obj instanceof Boolean) return ((Boolean) obj).booleanValue();
		if(obj instanceof String) return Boolean.parseBoolean((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
