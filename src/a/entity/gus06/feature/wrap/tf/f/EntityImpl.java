package a.entity.gus06.feature.wrap.tf.f;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		F f = (F) o[1];
		
		return new Wrap(t,f);
	}
	
	
	
	
	
	private class Wrap implements F
	{
		private T t;
		private F f;
		
		public Wrap(T t, F f)
		{
			this.t = t;
			this.f = f;
		}
		
		public boolean f(Object obj) throws Exception
		{
			obj = t.t(obj);
			return f.f(obj);
		}
	}
}
