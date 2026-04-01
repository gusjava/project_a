package a.entity.gus06.feature.wrap.tr.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		R r = (R) o[1];
		
		return new Wrap(t,r);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private T t;
		private R r;
		
		public Wrap(T t1, R r)
		{
			this.t = t;
			this.r = r;
		}
		
		public Object t(Object obj) throws Exception
		{
			String s = (String) t.t(obj);
			return r.r(s);
		}
	}
}
