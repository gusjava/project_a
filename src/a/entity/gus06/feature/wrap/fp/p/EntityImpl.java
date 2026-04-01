package a.entity.gus06.feature.wrap.fp.p;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160414";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		P p = (P) o[1];
		
		return new Wrap(f,p);
	}
	
	
	
	
	
	private class Wrap implements P
	{
		private F f;
		private P p;
		
		public Wrap(F f, P p)
		{
			this.f = f;
			this.p = p;
		}
		
		public void p(Object obj) throws Exception
		{
			if(f.f(obj)) p.p(obj);
		}
	}
}
