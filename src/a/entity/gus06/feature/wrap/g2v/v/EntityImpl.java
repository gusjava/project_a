package a.entity.gus06.feature.wrap.g2v.v;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160805";}

	
	
	public Object t(Object obj) throws Exception
	{
		G g = (G) obj;
		return new Wrap(g);
	}
	
	
	
	
	
	private class Wrap implements V
	{
		private G g;
		public Wrap(G g) {this.g = g;}
		
		public void v(String key, Object obj) throws Exception
		{
			V v = (V) g.g();
			v.v(key,obj);
		}
	}
}
