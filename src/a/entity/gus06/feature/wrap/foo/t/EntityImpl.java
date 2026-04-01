package a.entity.gus06.feature.wrap.foo.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160413";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		F f = (F) o[0];
		Object obj1 = o[1];
		Object obj2 = o[2];
		
		return new Wrap(f,obj1,obj2);
	}
	
	
	
	
	
	private class Wrap implements T
	{
		private F f;
		private Object obj1;
		private Object obj2;
		
		public Wrap(F f, Object obj1, Object obj2)
		{
			this.f = f;
			this.obj1 = obj1;
			this.obj2 = obj2;
		}
		
		public Object t(Object obj) throws Exception
		{
			return f.f(obj) ? obj1 : obj2;
		}
	}
}
