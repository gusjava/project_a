package a.entity.gus06.feature.wrap.g2p.p;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160805";}

	
	
	public Object t(Object obj) throws Exception
	{
		G g = (G) obj;
		return new Wrap(g);
	}
	
	
	
	
	
	private class Wrap implements P
	{
		private G g;
		public Wrap(G g) {this.g = g;}
		
		public void p(Object obj) throws Exception
		{
			P p = (P) g.g();
			p.p(obj);
		}
	}
}
