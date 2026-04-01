package a.entity.gus06.feature.wrap.th.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		H h = (H) o[1];
		
		return new Wrap(t,h);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private T t;
		private H h;
		
		public Wrap(T t, H h)
		{
			this.t = t;
			this.h = h;
		}
		
		public Object t(Object obj) throws Exception
		{
			Double v = (Double) t.t(obj);
			return Double.valueOf(h.h(v.doubleValue()));
		}
	}
}
