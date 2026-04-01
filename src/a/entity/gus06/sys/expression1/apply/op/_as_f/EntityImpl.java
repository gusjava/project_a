package a.entity.gus06.sys.expression1.apply.op._as_f;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151109";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof F) return new F1((F) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class F1 implements F
	{
		private F f;
		public F1(F f){this.f = f;}
		
		public boolean f(Object obj) throws Exception
		{return f.f(obj);}
	}
}