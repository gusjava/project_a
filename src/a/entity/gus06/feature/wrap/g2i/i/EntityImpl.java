package a.entity.gus06.feature.wrap.g2i.i;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160805";}

	
	
	public Object t(Object obj) throws Exception
	{
		G g = (G) obj;
		return new Wrap(g);
	}
	
	
	
	
	
	private class Wrap implements I
	{
		private G g;
		public Wrap(G g) {this.g = g;}
		
		public Object i() throws Exception
		{
			I i = (I) g.g();
			return i.i();
		}
	}
}
