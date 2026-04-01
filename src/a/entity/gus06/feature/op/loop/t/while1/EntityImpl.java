package a.entity.gus06.feature.op.loop.t.while1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160309";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		F f = (F) o[1];
		
		return new T1(t,f);
	}
	
	private class T1 implements T
	{
		private T t;
		private F f;
		
		public T1(T t, F f)
		{
			this.t = t;
			this.f = f;
		}
		
		public Object t(Object obj) throws Exception
		{
			Object r = obj;
			while(f.f(r)) r = t.t(r);
			return r;
		}
	}
}
