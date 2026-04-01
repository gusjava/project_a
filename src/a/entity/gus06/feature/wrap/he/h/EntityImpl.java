package a.entity.gus06.feature.wrap.he.h;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180309";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		H h = (H) o[0];
		E e = (E) o[1];
		
		return new Wrap(h,e);
	}
	
	
	
	
	
	private class Wrap implements H
	{
		private H h;
		private E e;
		
		public Wrap(H h, E e)
		{
			this.h = h;
			this.e = e;
		}
		
		public double h(double obj) throws Exception
		{
			double v = h.h(obj);
			e.e();
			return v;
		}
	}
}
