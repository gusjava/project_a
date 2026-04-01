package a.entity.gus06.sys.expression1.apply.op._math_pi;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160613";}

	public static final String T = "constant";
	
	
	public Object t(Object obj) throws Exception
	{
		return Double.valueOf(Math.PI);
	}
}