package a.entity.gus06.feature.wrap.frr.r;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		R r1 = (R) o[1];
		R r2 = (R) o[2];
		
		return new Wrap(f,r1,r2);
	}
	
	
	
	
	
	private class Wrap implements R
	{
		private F f;
		private R r1;
		private R r2;
		
		public Wrap(F f, R r1, R r2)
		{
			this.f = f;
			this.r1 = r1;
			this.r2 = r2;
		}
		
		public Object r(String key) throws Exception
		{
			R r = f.f(key) ? r1 : r2;
			return r!=null ? r.r(key) : null;
		}
	}
}
