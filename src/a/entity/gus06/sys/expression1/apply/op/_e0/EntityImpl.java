package a.entity.gus06.sys.expression1.apply.op._e0;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151116";}

	public static final String T = "constant";
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return new E0();
	}
	
	private class E0 implements E
	{
		public void e() throws Exception
		{}
	}
}