package a.entity.gus06.feature.wrap.it.i;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		I i = (I) o[0];
		T t = (T) o[1];
		
		return new Wrap(i,t);
	}
	
	
	
	
	
	private class Wrap implements I
	{
		private I i;
		private T t;
		
		public Wrap(I i, T t)
		{
			this.i = i;
			this.t = t;
		}
		
		public Object i() throws Exception
		{
			Object obj = i.i();
			return t.t(obj);
		}
	}
}
