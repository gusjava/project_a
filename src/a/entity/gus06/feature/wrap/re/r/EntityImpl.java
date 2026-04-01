package a.entity.gus06.feature.wrap.re.r;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180309";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R r = (R) o[0];
		E e = (E) o[1];
		
		return new Wrap(r,e);
	}
	
	
	
	
	
	private class Wrap implements R
	{
		private R r;
		private E e;
		
		public Wrap(R r, E e)
		{
			this.r = r;
			this.e = e;
		}
		
		public Object r(String key) throws Exception
		{
			Object obj = r.r(key);
			e.e();
			return obj;
		}
	}
}
