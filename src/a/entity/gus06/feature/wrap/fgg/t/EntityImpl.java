package a.entity.gus06.feature.wrap.fgg.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160414";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		G g1 = (G) o[1];
		G g2 = (G) o[2];
		
		return new Wrap(f,g1,g2);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private F f;
		private G g1;
		private G g2;
		
		public Wrap(F f, G g1, G g2)
		{
			this.f = f;
			this.g1 = g1;
			this.g2 = g2;
		}
		
		public Object t(Object obj) throws Exception
		{
			G g = f.f(obj) ? g1 : g2;
			return g!=null ? g.g() : null;
		}
	}
}
