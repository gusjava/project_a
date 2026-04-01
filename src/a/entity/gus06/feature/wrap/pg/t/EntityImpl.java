package a.entity.gus06.feature.wrap.pg.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160414";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		P p = (P) o[0];
		G g = (G) o[1];
		
		return new Wrap(p,g);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private P p;
		private G g;
		
		public Wrap(P p, G g)
		{
			this.p = p;
			this.g = g;
		}
		
		public Object t(Object obj) throws Exception
		{
			p.p(obj);
			return g.g();
		}
	}
}
