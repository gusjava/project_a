package a.entity.gus06.feature.wrap.fg.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161211";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		G g = (G) o[1];
		
		return new Wrap(f,g);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private F f;
		private G g;
		
		public Wrap(F f, G g)
		{
			this.f = f;
			this.g = g;
		}
		
		public Object t(Object obj) throws Exception
		{
			return f.f(obj) ? g.g() : null;
		}
	}
}
