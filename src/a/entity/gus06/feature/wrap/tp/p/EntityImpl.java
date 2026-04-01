package a.entity.gus06.feature.wrap.tp.p;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		P p = (P) o[1];
		
		return new Wrap(t,p);
	}
	
	
	
	
	
	private class Wrap implements P
	{
		private T t;
		private P p;
		
		public Wrap(T t, P p)
		{
			this.t = t;
			this.p = p;
		}
		
		public void p(Object obj) throws Exception
		{
			obj = t.t(obj);
			p.p(obj);
		}
	}
}
