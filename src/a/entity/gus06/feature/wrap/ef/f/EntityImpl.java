package a.entity.gus06.feature.wrap.ef.f;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180309";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		E e = (E) o[0];
		F f = (F) o[1];
		
		return new Wrap(e,f);
	}
	
	
	
	
	
	private class Wrap implements F
	{
		private E e;
		private F f;
		
		public Wrap(E e, F f)
		{
			this.e = e;
			this.f = f;
		}
		
		public boolean f(Object obj) throws Exception
		{
			e.e();
			return f.f(obj);
		}
	}
}
