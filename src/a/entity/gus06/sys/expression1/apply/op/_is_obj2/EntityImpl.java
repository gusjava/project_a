package a.entity.gus06.sys.expression1.apply.op._is_obj2;

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
		
		if(obj instanceof Object[]) return  Boolean.TRUE;
		if(obj instanceof int[]) return  Boolean.TRUE;
		if(obj instanceof short[]) return  Boolean.TRUE;
		if(obj instanceof long[]) return  Boolean.TRUE;
		if(obj instanceof boolean[]) return  Boolean.TRUE;
		if(obj instanceof double[]) return  Boolean.TRUE;
		if(obj instanceof float[]) return  Boolean.TRUE;
		if(obj instanceof char[]) return  Boolean.TRUE;
		if(obj instanceof byte[]) return  Boolean.TRUE;
		
		if(obj instanceof Collection) return  Boolean.TRUE;
		if(obj instanceof Map) return  Boolean.TRUE;
		
		return Boolean.FALSE;
	}
}
