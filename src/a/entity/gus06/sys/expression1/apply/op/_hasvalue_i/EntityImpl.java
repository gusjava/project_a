package a.entity.gus06.sys.expression1.apply.op._hasvalue_i;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221008";}


	private Service mapContainsValue;
	
	
	public EntityImpl() throws Exception
	{
		mapContainsValue = Outside.service(this,"gus06.map.contains.value.maptof.i");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof Map) return mapContainsValue.t(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
}