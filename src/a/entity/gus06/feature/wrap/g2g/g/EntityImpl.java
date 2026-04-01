package a.entity.gus06.feature.wrap.g2g.g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160805";}

	
	
	public Object t(Object obj) throws Exception
	{
		G g = (G) obj;
		return new Wrap(g);
	}
	
	
	
	
	
	private class Wrap implements G
	{
		private G g;
		public Wrap(G g) {this.g = g;}
		
		public Object g() throws Exception
		{
			G g2 = (G) g.g();
			return g2.g();
		}
	}
}
