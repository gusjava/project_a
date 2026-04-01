package a.entity.gus06.convert.ttop;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160816";}


	public Object t(Object obj) throws Exception
	{return new P1((T) obj);}
	
		
	
	private class P1 implements P
	{
		private T t;
		public P1(T t) {this.t = t;}
		
		public void p(Object obj) throws Exception
		{
			E e = (E) t.t(obj);
			e.e();
		}
	}
}
