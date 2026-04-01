package a.entity.gus06.feature.wrap.gfp.e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160414";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		F f = (F) o[1];
		P p = (P) o[2];
		
		return new Wrap(g,f,p);
	}
	
	
	
		
	
	private class Wrap implements E
	{
		private G g;
		private F f;
		private P p;
		
		public Wrap(G g, F f, P p)
		{
			this.g = g;
			this.p = p;
			this.f = f;
		}
		
		public void e() throws Exception
		{
			Object obj = g.g();
			if(f.f(obj)) p.p(obj);
		}
	}
}
