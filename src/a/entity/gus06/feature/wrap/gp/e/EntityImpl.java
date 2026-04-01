package a.entity.gus06.feature.wrap.gp.e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		P p = (P) o[1];
		
		return new Wrap(g,p);
	}
	
	
	
	
	
	private class Wrap implements E
	{
		private G g;
		private P p;
		
		public Wrap(G g, P p)
		{
			this.g = g;
			this.p = p;
		}
		
		public void e() throws Exception
		{
			Object obj = g.g();
			p.p(obj);
		}
	}
}
