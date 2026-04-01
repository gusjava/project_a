package a.entity.gus06.feature.wrap.te.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180309";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		E e = (E) o[1];
		
		return new Wrap(t,e);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private T t;
		private E e;
		
		public Wrap(T t, E e)
		{
			this.t = t;
			this.e = e;
		}
		
		public Object t(Object obj) throws Exception
		{
			obj = t.t(obj);
			e.e();
			return obj;
		}
	}
}
