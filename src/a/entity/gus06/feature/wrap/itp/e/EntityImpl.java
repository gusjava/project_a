package a.entity.gus06.feature.wrap.itp.e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		I i = (I) o[0];
		T t = (T) o[1];
		P p = (P) o[2];
		
		return new Wrap(i,t,p);
	}
	
	
	
		
	
	private class Wrap implements E
	{
		private I i;
		private T t;
		private P p;
		
		public Wrap(I i, T t, P p)
		{
			this.i = i;
			this.t = t;
			this.p = p;
		}
		
		public void e() throws Exception
		{
			Object obj = i.i();
			obj = t.t(obj);
			p.p(obj);
		}
	}
}
