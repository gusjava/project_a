package a.entity.gus06.feature.wrap.ftt.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160413";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		T t1 = (T) o[1];
		T t2 = (T) o[2];
		
		return new Wrap(f,t1,t2);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private F f;
		private T t1;
		private T t2;
		
		public Wrap(F f, T t1, T t2)
		{
			this.f = f;
			this.t1 = t1;
			this.t2 = t2;
		}
		
		public Object t(Object obj) throws Exception
		{
			T t = f.f(obj) ? t1 : t2;
			return t!=null ? t.t(obj) : null;
		}
	}
}
