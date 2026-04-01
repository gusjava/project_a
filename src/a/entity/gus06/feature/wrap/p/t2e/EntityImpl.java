package a.entity.gus06.feature.wrap.p.t2e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160518";}

	
	
	public Object t(Object obj) throws Exception
	{
		P p = (P) obj;
		return new Wrap(p);
	}
	
	
	private class Wrap implements T
	{
		private P p;
		public Wrap(P p) {this.p = p;}
		
		public Object t(Object obj) throws Exception
		{return new E1(p,obj);}
	}
	
	private class E1 implements E
	{
		private P p;
		private Object data;
		
		public E1(P p, Object data)
		{
			this.p = p;
			this.data = data;
		}
		public void e() throws Exception
		{p.p(data);}
	}
}
