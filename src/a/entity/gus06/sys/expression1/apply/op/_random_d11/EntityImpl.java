package a.entity.gus06.sys.expression1.apply.op._random_d11;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20171003";}

	public static final String T = "constant";
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return Double.valueOf(Math.random()*2-1);
	}
}
