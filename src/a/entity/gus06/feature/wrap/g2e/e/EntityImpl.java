package a.entity.gus06.feature.wrap.g2e.e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160805";}

	
	
	public Object t(Object obj) throws Exception
	{
		G g = (G) obj;
		return new Wrap(g);
	}
	
	
	
	
	
	private class Wrap implements E
	{
		private G g;
		public Wrap(G g) {this.g = g;}
		
		public void e() throws Exception
		{
			E e = (E) g.g();
			e.e();
		}
	}
}
