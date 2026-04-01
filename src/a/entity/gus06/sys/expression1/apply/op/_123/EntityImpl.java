package a.entity.gus06.sys.expression1.apply.op._123;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160730";}

	public static final String T = "constant";
	
	public static final String DIGITS = "0123456789";
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return DIGITS;
	}
}
