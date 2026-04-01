package a.entity.gus06.feature.wrap.ht.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		H h = (H) o[0];
		T t = (T) o[1];
		
		return new Wrap(h,t);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private H h;
		private T t;
		
		public Wrap(H h, T t)
		{
			this.h = h;
			this.t = t;
		}
		
		public Object t(Object obj) throws Exception
		{
			double v = h.h(((Double) obj).doubleValue());
			return t.t(Double.valueOf(v));
		}
	}
}
