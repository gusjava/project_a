package a.entity.gus06.feature.wrap.gh.g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		H h = (H) o[1];
		
		return new Wrap(g,h);
	}
	
	
	
	
	
	private class Wrap implements G
	{
		private G g;
		private H h;
		
		public Wrap(G g, H h)
		{
			this.g = g;
			this.h = h;
		}
		
		public Object g() throws Exception
		{
			Double v = (Double) g.g();
			return Double.valueOf(h.h(v.doubleValue()));
		}
	}
}
