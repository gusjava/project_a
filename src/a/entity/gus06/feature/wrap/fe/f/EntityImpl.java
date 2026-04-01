package a.entity.gus06.feature.wrap.fe.f;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180309";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		E e = (E) o[1];
		
		return new Wrap(f,e);
	}
	
	
	
	
	
	private class Wrap implements F
	{
		private F f;
		private E e;
		
		public Wrap(F f, E e)
		{
			this.f = f;
			this.e = e;
		}
		
		public boolean f(Object obj) throws Exception
		{
			boolean r = f.f(obj);
			e.e();
			return r;
		}
	}
}
