package a.entity.gus06.feature.wrap.fii.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161211";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		I i1 = (I) o[1];
		I i2 = (I) o[2];
		
		return new Wrap(f,i1,i2);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private F f;
		private I i1;
		private I i2;
		
		public Wrap(F f, I i1, I i2)
		{
			this.f = f;
			this.i1 = i1;
			this.i2 = i2;
		}
		
		public Object t(Object obj) throws Exception
		{
			I i = f.f(obj) ? i1 : i2;
			return i!=null ? i.i() : null;
		}
	}
}
