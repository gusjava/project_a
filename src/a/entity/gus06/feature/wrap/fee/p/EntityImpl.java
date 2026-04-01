package a.entity.gus06.feature.wrap.fee.p;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161211";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		E e1 = (E) o[1];
		E e2 = (E) o[2];
		
		return new Wrap(f,e1,e2);
	}
	
	
	
	
	
	private class Wrap implements P
	{
		private F f;
		private E e1;
		private E e2;
		
		public Wrap(F f, E e1, E e2)
		{
			this.f = f;
			this.e1 = e1;
			this.e2 = e2;
		}
		
		public void p(Object obj) throws Exception
		{
			E e = f.f(obj) ? e1 : e2;
			if(e!=null) e.e();
		}
	}
}
