package a.entity.gus06.sys.expression1.apply.op._exit;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160613";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Integer) return new E1((Integer) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class E1 implements E
	{
		private int code;
		public E1(Integer n)
		{this.code = n.intValue();}
		
		public void e() throws Exception
		{System.exit(code);}
	}
}
