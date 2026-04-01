package a.entity.gus06.feature.wrap.gt.g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		T t = (T) o[1];
		
		return new Wrap(g,t);
	}
	
	
	
	
	
	private class Wrap implements G
	{
		private G g;
		private T t;
		
		public Wrap(G g, T t)
		{
			this.g = g;
			this.t = t;
		}
		
		public Object g() throws Exception
		{
			Object obj = g.g();
			return t.t(obj);
		}
	}
}
