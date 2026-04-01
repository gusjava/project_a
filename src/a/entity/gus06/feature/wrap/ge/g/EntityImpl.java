package a.entity.gus06.feature.wrap.ge.g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180309";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		E e = (E) o[1];
		
		return new Wrap(g,e);
	}
	
	
	
	
	
	private class Wrap implements G
	{
		private G g;
		private E e;
		
		public Wrap(G g, E e)
		{
			this.g = g;
			this.e = e;
		}
		
		public Object g() throws Exception
		{
			Object obj = g.g();
			e.e();
			return obj;
		}
	}
}
