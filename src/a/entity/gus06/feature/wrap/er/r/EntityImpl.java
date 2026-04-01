package a.entity.gus06.feature.wrap.er.r;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180309";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		E e = (E) o[0];
		R r = (R) o[1];
		
		return new Wrap(e,r);
	}
	
	
	
	
	
	private class Wrap implements R
	{
		private E e;
		private R r;
		
		public Wrap(E e, R r)
		{
			this.e = e;
			this.r = r;
		}
		
		public Object r(String key) throws Exception
		{
			e.e();
			return r.r(key);
		}
	}
}
