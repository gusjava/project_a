package a.entity.gus06.feature.op.loop.e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160305";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		E e = (E) o[0];
		int n = toInt(o[1]);
		
		return new E1(e,n);
	}
	
	private int toInt(Object obj) throws Exception
	{
		if(obj instanceof Integer) return ((Integer)obj).intValue();
		if(obj instanceof String) return Integer.parseInt((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class E1 implements E
	{
		private E e;
		private int n;
		
		public E1(E e, int n)
		{
			this.e = e;
			this.n = n;
		}
		
		public void e() throws Exception
		{
			for(int i=0;i<n;i++) e.e();
		}
	}
}
