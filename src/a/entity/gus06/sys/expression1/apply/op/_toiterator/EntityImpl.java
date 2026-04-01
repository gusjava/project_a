package a.entity.gus06.sys.expression1.apply.op._toiterator;

import a.framework.*;
import java.util.Collection;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151115";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Collection)
			return ((Collection) obj).iterator();
		if(obj instanceof Map)
			return ((Map) obj).keySet().iterator();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
