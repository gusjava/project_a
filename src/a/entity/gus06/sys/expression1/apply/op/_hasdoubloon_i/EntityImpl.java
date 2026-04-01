package a.entity.gus06.sys.expression1.apply.op._hasdoubloon_i;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250211";}


	private Service performList;
	private Service performSet;
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.data.list.string.filter.hasdoubloon_i");
		performSet = Outside.service(this,"gus06.data.set.string.filter.hasdoubloon_i");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof List)
			return Boolean.valueOf(performList.f(value));
		if(value instanceof Set)
			return Boolean.valueOf(performSet.f(value));
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}