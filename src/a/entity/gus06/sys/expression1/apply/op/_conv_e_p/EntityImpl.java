package a.entity.gus06.sys.expression1.apply.op._conv_e_p;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250529";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof E) return new P1((E) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class P1 implements P
	{
		private E e;
		public P1(E e){this.e = e;}
		
		public void p(Object obj) throws Exception
		{e.e();}
	}
}