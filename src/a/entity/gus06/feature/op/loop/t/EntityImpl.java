package a.entity.gus06.feature.op.loop.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160305";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		int n = toInt(o[1]);
		
		return new T1(t,n);
	}
	
	private int toInt(Object obj) throws Exception
	{
		if(obj instanceof Integer) return ((Integer)obj).intValue();
		if(obj instanceof String) return Integer.parseInt((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private T t;
		private int n;
		
		public T1(T t, int n)
		{
			this.t = t;
			this.n = n;
		}
		
		public Object t(Object obj) throws Exception
		{
			Object r = obj;
			for(int i=0;i<n;i++) r = t.t(r);
			return r;
		}
	}
}
