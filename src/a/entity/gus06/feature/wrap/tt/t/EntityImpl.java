package a.entity.gus06.feature.wrap.tt.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t1 = (T) o[0];
		T t2 = (T) o[1];
		
		return new Wrap(t1,t2);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private T t1;
		private T t2;
		
		public Wrap(T t1, T t2)
		{
			this.t1 = t1;
			this.t2 = t2;
		}
		
		public Object t(Object obj) throws Exception
		{
			obj = t1.t(obj);
			return t2.t(obj);
		}
	}
}
