package a.entity.gus06.sys.expression1.apply.op._jvm_mem_max;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190522";}

	public static final String T = "constant";
	

	private Runtime r;
		
	public EntityImpl() throws Exception
	{
		r = Runtime.getRuntime();
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return r.maxMemory();
	}
}
