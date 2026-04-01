package a.entity.gus06.feature.wrap.ip.e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		I i = (I) o[0];
		P p = (P) o[1];
		
		return new Wrap(i,p);
	}
	
	
	
	
	
	private class Wrap implements E
	{
		private I i;
		private P p;
		
		public Wrap(I i, P p)
		{
			this.i = i;
			this.p = p;
		}
		
		public void e() throws Exception
		{
			Object obj = i.i();
			p.p(obj);
		}
	}
}
