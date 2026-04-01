package a.entity.gus06.feature.wrap.gr.g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		R r = (R) o[1];
		
		return new Wrap(g,r);
	}
	
	
	
	
	
	private class Wrap implements G
	{
		private G g;
		private R r;
		
		public Wrap(G g, R r)
		{
			this.g = g;
			this.r = r;
		}
		
		public Object g() throws Exception
		{
			String s = (String) g.g();
			return r.r(s);
		}
	}
}
