package a.entity.gus06.sys.expression1.apply.op._g0;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151116";}

	public static final String T = "constant";
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new G0();
	}
	
	private class G0 implements G
	{
		public Object g() throws Exception
		{return null;}
	}
}