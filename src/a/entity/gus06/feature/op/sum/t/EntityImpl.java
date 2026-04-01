package a.entity.gus06.feature.op.sum.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160111";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T[] tt = (T[]) o[0];
		T sumT = (T) o[1];
		
		return new T1(tt,sumT);
	}
	
	
	private class T1 implements T
	{
		private T[] tt;
		private T sumT;
		
		public T1(T[] tt, T sumT)
		{
			this.tt = tt;
			this.sumT = sumT;
		}
		
		public Object t(Object obj) throws Exception
		{
			Object[] r = new Object[tt.length];
			for(int i=0;i<tt.length;i++) r[i] = tt[i].t(obj);
			return sumT.t(r);
		}
	}
}
