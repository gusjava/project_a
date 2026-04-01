package a.entity.gus06.feature.wrap.gtp.e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150707";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		T t = (T) o[1];
		P p = (P) o[2];
		
		return new Wrap(g,t,p);
	}
	
	
	
		
	
	private class Wrap implements E
	{
		private G g;
		private T t;
		private P p;
		
		public Wrap(G g, T t, P p)
		{
			this.g = g;
			this.t = t;
			this.p = p;
		}
		
		public void e() throws Exception
		{
			Object obj = g.g();
			obj = t.t(obj);
			p.p(obj);
		}
	}
}
