package a.entity.gus06.sys.expression1.apply.op._hasdoubloon;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250211";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.list.string.filter.hasdoubloon");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return perform.t(value);
		
		if(value instanceof List)
			return Boolean.valueOf(perform.f(value));
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}