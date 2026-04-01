package a.entity.gus06.sys.expression1.apply.op._h_rand11_s;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20171004";}

	public static final String T = "constant";


	private Service function;
	
	public EntityImpl() throws Exception
	{
		function = Outside.service(this,"gus06.math.function.h.rand11.s");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return function;
	}
}
