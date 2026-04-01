package a.entity.gus06.sys.expression1.apply.op._random_d01;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160815";}

	public static final String T = "constant";
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return Double.valueOf(Math.random());
	}
}
