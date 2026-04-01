package a.entity.gus06.feature.op.sum.t.nnn;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180330";}

	
	public Object t(Object obj) throws Exception
	{return new T1((T[]) obj);}
	
	
	private class T1 implements T
	{
		private T[] tt;
		public T1(T[] tt){this.tt = tt;}
		
		public Object t(Object obj) throws Exception
		{
			for(T t:tt)
			{
				Object r = t.t(obj);
				if(r!=null) return r; 
			}
			return null;
		}
	}
}
