package a.entity.gus06.sys.expression1.apply.op._is_operatorname;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160711";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return Boolean.FALSE;
		if(value instanceof String) return Boolean.valueOf(opMap.containsKey(value));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
