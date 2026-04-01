package a.entity.gus06.sys.expression1.apply.op._to_f;

import a.framework.*;
import java.util.Map;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151113";}


	private Service builder;
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.expression1.builder2.f");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof F) return value;
		
		if(value instanceof String)
			return builder.t(obj);
		if(value instanceof Map)
			return builder.t(obj);
		if(value instanceof Collection)
			return builder.t(obj);
		if(value instanceof Number)
			return builder.t(obj);
		if(value instanceof T)
			return builder.t(obj);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}
