package a.entity.gus06.sys.expression1.apply.op._haskey;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160120";}


	private Service mapContainsKey;
	
	
	public EntityImpl() throws Exception
	{
		mapContainsKey = Outside.service(this,"gus06.map.contains.key.maptof");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof Map) return mapContainsKey.t(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
}
