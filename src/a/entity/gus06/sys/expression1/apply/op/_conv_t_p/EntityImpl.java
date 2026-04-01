package a.entity.gus06.sys.expression1.apply.op._conv_t_p;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250529";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof T) return new P1((T) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class P1 implements P
	{
		private T t;
		public P1(T t){this.t = t;}
		
		public void p(Object obj) throws Exception
		{t.t(obj);}
	}
}