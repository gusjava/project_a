package a.entity.gus06.sys.expression1.apply.op._p_to_pe;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160923";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof P) return new P1((P) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class P1 implements P, E
	{
		private P p;
		private StringBuffer sb;
		
		public P1(P p)
		{
			this.p = p;
			sb = new StringBuffer();
		}
		
		public void p(Object obj) throws Exception
		{sb.append(obj);}
		
		public void e() throws Exception
		{
			p.p(sb.toString());
			sb = new StringBuffer();
		}
	}
}