package a.entity.gus06.feature.wrap.fi.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161211";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		I i = (I) o[1];
		
		return new Wrap(f,i);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private F f;
		private I i;
		
		public Wrap(F f, I i)
		{
			this.f = f;
			this.i = i;
		}
		
		public Object t(Object obj) throws Exception
		{
			return f.f(obj) ? i.i() : null;
		}
	}
}
