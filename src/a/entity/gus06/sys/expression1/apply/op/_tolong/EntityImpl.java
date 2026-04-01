package a.entity.gus06.sys.expression1.apply.op._tolong;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151109";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj.equals("")) return null;
		
		if(obj instanceof Number) return Long.valueOf(((Number) obj).longValue());
		if(obj instanceof String) return Long.valueOf((String) obj);
		if(obj instanceof Date) return Long.valueOf(((Date) obj).getTime());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}