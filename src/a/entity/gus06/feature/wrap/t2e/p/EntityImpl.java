package a.entity.gus06.feature.wrap.t2e.p;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160518";}

	
	
	public Object t(Object obj) throws Exception
	{
		T t = (T) obj;
		return new Wrap(t);
	}
	
	
	
	
	
	private class Wrap implements P
	{
		private T t;
		public Wrap(T t) {this.t = t;}
		
		public void p(Object obj) throws Exception
		{
			E e = (E) t.t(obj);
			e.e();
		}
	}
}
