package a.entity.gus06.data.filter.check.many;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160927";}
	
	
	private Service filterList;
	private Service filterSet;
	private Service filterMap;
	
	
	public EntityImpl() throws Exception
	{
		filterList = Outside.service(this,"gus06.list.check.many");
		filterSet = Outside.service(this,"gus06.set.check.many");
		filterMap = Outside.service(this,"gus06.map.key.check.many");
	}	
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		
		if(input instanceof List) return filterList.f(obj);
		if(input instanceof Set) return filterSet.f(obj);
		if(input instanceof Map) return filterMap.f(obj);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
}
