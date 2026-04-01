package a.entity.gus06.feature.wrap.gf.g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		F f = (F) o[1];
		
		return new Wrap(g,f);
	}
	
	
	
	
	
	private class Wrap implements G
	{
		private G g;
		private F f;
		
		public Wrap(G g, F f)
		{
			this.g = g;
			this.f = f;
		}
		
		public Object g() throws Exception
		{
			Object obj = g.g();
			return Boolean.valueOf(f.f(obj));
		}
	}
}