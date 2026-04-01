package a.entity.gus06.sys.expression1.apply.op._bigint;

import a.framework.*;
import java.math.BigInteger;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20181226";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Long) return BigInteger.valueOf((Long) obj);
		if(obj instanceof Integer) return BigInteger.valueOf((Integer) obj);
		if(obj instanceof String) return new BigInteger((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
