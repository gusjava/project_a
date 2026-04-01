package a.entity.gus06.sys.expression1.apply.op._e_throw_runtime;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191219";}



	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		if(value instanceof String) return new E1(new RuntimeException((String) value));
		if(value instanceof RuntimeException) return new E1((RuntimeException) value);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class E1 implements E
	{
		private RuntimeException e;
		public E1(RuntimeException e){this.e = e;}
		
		public void e() throws Exception
		{throw e;}
	}
}
