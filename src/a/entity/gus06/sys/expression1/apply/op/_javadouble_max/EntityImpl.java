package a.entity.gus06.sys.expression1.apply.op._javadouble_max;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231118";}

	public static final String T = "constant";
	

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return Double.MAX_VALUE;
	}
}