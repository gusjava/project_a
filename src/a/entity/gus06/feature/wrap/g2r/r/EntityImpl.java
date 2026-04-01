package a.entity.gus06.feature.wrap.g2r.r;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160805";}

	
	
	public Object t(Object obj) throws Exception
	{
		G g = (G) obj;
		return new Wrap(g);
	}
	
	
	
	
	
	private class Wrap implements R
	{
		private G g;
		public Wrap(G g) {this.g = g;}
		
		public Object r(String key) throws Exception
		{
			R r = (R) g.g();
			return r.r(key);
		}
	}
}
