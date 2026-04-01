package a.entity.gus06.feature.wrap.ft.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160413";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		T t = (T) o[1];
		
		return new Wrap(f,t);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private F f;
		private T t;
		
		public Wrap(F f, T t)
		{
			this.f = f;
			this.t = t;
		}
		
		public Object t(Object obj) throws Exception
		{
			return f.f(obj) ? t.t(obj) : obj;
		}
	}
}
