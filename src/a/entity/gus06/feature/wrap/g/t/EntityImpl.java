package a.entity.gus06.feature.wrap.g.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		G g = (G) obj;
		return new Wrap(g);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private G g;
		
		public Wrap(G g)
		{
			this.g = g;
		}
		
		public Object t(Object obj) throws Exception
		{
			return g.g();
		}
	}
}
