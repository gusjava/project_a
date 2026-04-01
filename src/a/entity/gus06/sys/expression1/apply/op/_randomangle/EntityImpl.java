package a.entity.gus06.sys.expression1.apply.op._randomangle;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170427";}

	public static final String T = "constant";
	
	public static final double FULL = Math.PI*2;


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return Double.valueOf(Math.random()*FULL);
	}
}
