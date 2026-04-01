package a.entity.gus06.feature.wrap.to.g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160225";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		Object data = o[1];
		
		return new Wrap(t,data);
	}
	
	
	
	
	
	private class Wrap implements G
	{
		private T t;
		private Object data;
		
		public Wrap(T t, Object data)
		{
			this.t = t;
			this.data = data;
		}
		
		public Object g() throws Exception
		{
			return t.t(data);
		}
	}
}
