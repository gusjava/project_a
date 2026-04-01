package a.entity.gus06.feature.wrap.fr.r;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		R r = (R) o[1];
		
		return new Wrap(f,r);
	}
	
	
	
	
	
	private class Wrap implements R
	{
		private F f;
		private R r;
		
		public Wrap(F f, R r)
		{
			this.f = f;
			this.r = r;
		}
		
		public Object r(String key) throws Exception
		{
			return f.f(key) ? r.r(key) : null;
		}
	}
}
