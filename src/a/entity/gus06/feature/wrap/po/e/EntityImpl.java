package a.entity.gus06.feature.wrap.po.e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151031";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		P p = (P) o[0];
		Object data = o[1];
		
		return new Wrap(p,data);
	}
	
	
	
	
	
	private class Wrap implements E
	{
		private P p;
		private Object data;
		
		public Wrap(P p, Object data)
		{
			this.p = p;
			this.data = data;
		}
		
		public void e() throws Exception
		{
			p.p(data);
		}
	}
}
