package a.entity.gus06.feature.wrap.ep.p;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180309";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		E e = (E) o[0];
		P p = (P) o[1];
		
		return new Wrap(e,p);
	}
	
	
	
	
	
	private class Wrap implements P
	{
		private E e;
		private P p;
		
		public Wrap(E e, P p)
		{
			this.e = e;
			this.p = p;
		}
		
		public void p(Object obj) throws Exception
		{
			e.e();
			p.p(obj);
		}
	}
}
