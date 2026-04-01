package a.entity.gus06.sys.expression1.apply.op._h_rand01;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20171003";}

	public static final String T = "constant";


	private Service function;
	
	public EntityImpl() throws Exception
	{
		function = Outside.service(this,"gus06.math.function.h.rand01");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return function;
	}
}
