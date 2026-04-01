package a.entity.gus06.feature.wrap.ie.i;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180309";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		I i = (I) o[0];
		E e = (E) o[1];
		
		return new Wrap(i,e);
	}
	
	
	
	
	
	private class Wrap implements I
	{
		private I i;
		private E e;
		
		public Wrap(I i, E e)
		{
			this.i = i;
			this.e = e;
		}
		
		public Object i() throws Exception
		{
			Object obj = i.i();
			e.e();
			return obj;
		}
	}
}
