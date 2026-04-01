package a.entity.gus06.feature.wrap.eh.h;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180309";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		E e = (E) o[0];
		H h = (H) o[1];
		
		return new Wrap(e,h);
	}
	
	
	
	
	
	private class Wrap implements H
	{
		private E e;
		private H h;
		
		public Wrap(E e, H h)
		{
			this.e = e;
			this.h = h;
		}
		
		public double h(double obj) throws Exception
		{
			e.e();
			return h.h(obj);
		}
	}
}
