package a.entity.gus06.feature.wrap.eg.g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161212";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		E e = (E) o[0];
		G g = (G) o[1];
		
		return new Wrap(e,g);
	}
	
	
	
	
	
	private class Wrap implements G
	{
		private E e;
		private G g;
		
		public Wrap(E e, G g)
		{
			this.e = e;
			this.g = g;
		}
		
		public Object g() throws Exception
		{
			e.e();
			return g.g();
		}
	}
}
