package a.entity.gus06.feature.wrap.fpp.p;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		P p1 = (P) o[1];
		P p2 = (P) o[2];
		
		return new Wrap(f,p1,p2);
	}
	
	
	
	
	
	private class Wrap implements P
	{
		private F f;
		private P p1;
		private P p2;
		
		public Wrap(F f, P p1, P p2)
		{
			this.f = f;
			this.p1 = p1;
			this.p2 = p2;
		}
		
		public void p(Object obj) throws Exception
		{
			P p = f.f(obj) ? p1 : p2;
			if(p!=null) p.p(obj);
		}
	}
}
