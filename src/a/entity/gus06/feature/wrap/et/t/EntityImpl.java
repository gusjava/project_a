package a.entity.gus06.feature.wrap.et.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180309";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		E e = (E) o[0];
		T t = (T) o[1];
		
		return new Wrap(e,t);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private E e;
		private T t;
		
		public Wrap(E e, T t)
		{
			this.e = e;
			this.t = t;
		}
		
		public Object t(Object obj) throws Exception
		{
			e.e();
			return t.t(obj);
		}
	}
}
