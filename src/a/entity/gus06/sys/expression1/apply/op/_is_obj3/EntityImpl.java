package a.entity.gus06.sys.expression1.apply.op._is_obj3;

import a.framework.*;
import java.util.Collection;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160816";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		
		if(obj instanceof Number) return Boolean.FALSE;
		if(obj instanceof Boolean) return Boolean.FALSE;
		if(obj instanceof String) return Boolean.FALSE;
		
		if(obj instanceof Object[]) return  Boolean.FALSE;
		if(obj instanceof int[]) return  Boolean.FALSE;
		if(obj instanceof short[]) return  Boolean.FALSE;
		if(obj instanceof long[]) return  Boolean.FALSE;
		if(obj instanceof boolean[]) return  Boolean.FALSE;
		if(obj instanceof double[]) return  Boolean.FALSE;
		if(obj instanceof float[]) return  Boolean.FALSE;
		if(obj instanceof char[]) return  Boolean.FALSE;
		if(obj instanceof byte[]) return  Boolean.FALSE;
		
		if(obj instanceof Collection) return  Boolean.FALSE;
		if(obj instanceof Map) return  Boolean.FALSE;
		
		return Boolean.TRUE;
	}
}
