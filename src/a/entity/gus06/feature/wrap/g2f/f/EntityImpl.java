package a.entity.gus06.feature.wrap.g2f.f;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160805";}

	
	
	public Object t(Object obj) throws Exception
	{
		G g = (G) obj;
		return new Wrap(g);
	}
	
	
	
	
	
	private class Wrap implements F
	{
		private G g;
		public Wrap(G g) {this.g = g;}
		
		public boolean f(Object obj) throws Exception
		{
			F f = (F) g.g();
			return f.f(obj);
		}
	}
}
