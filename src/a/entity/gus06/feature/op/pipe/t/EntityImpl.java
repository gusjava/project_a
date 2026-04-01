package a.entity.gus06.feature.op.pipe.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150707";}

	
	public Object t(Object obj) throws Exception
	{return new T1((T[]) obj);}
	
	
	private class T1 implements T
	{
		private T[] tt;
		public T1(T[] tt){this.tt = tt;}
		
		public Object t(Object obj) throws Exception
		{
			Object r = obj;
			for(T t:tt) r = t.t(r);
			return r;
		}
	}
}
