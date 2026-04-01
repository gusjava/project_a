package a.entity.gus06.feature.op.loop.p;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160305";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		P p = (P) o[0];
		int n = toInt(o[1]);
		
		return new P1(p,n);
	}
	
	private int toInt(Object obj) throws Exception
	{
		if(obj instanceof Integer) return ((Integer)obj).intValue();
		if(obj instanceof String) return Integer.parseInt((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class P1 implements P
	{
		private P p;
		private int n;
		
		public P1(P p, int n)
		{
			this.p = p;
			this.n = n;
		}
		
		public void p(Object obj) throws Exception
		{
			for(int i=0;i<n;i++) p.p(obj);
		}
	}
}
