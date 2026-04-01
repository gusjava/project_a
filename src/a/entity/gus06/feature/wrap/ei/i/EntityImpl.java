package a.entity.gus06.feature.wrap.ei.i;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180309";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		E e = (E) o[0];
		I i = (I) o[1];
		
		return new Wrap(e,i);
	}
	
	
	
	
	
	private class Wrap implements I
	{
		private E e;
		private I i;
		
		public Wrap(E e, I i)
		{
			this.e = e;
			this.i = i;
		}
		
		public Object i() throws Exception
		{
			e.e();
			return i.i();
		}
	}
}
