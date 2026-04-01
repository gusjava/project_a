package a.entity.gus06.data.perform.take.strict;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170419";}


	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
		
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		Object key = o[1];
		
		if(input instanceof List)
			return take((List) input, key);
		
		if(input instanceof Set)
			return take((Set) input, key);
		
		if(input instanceof Map)
			return take((Map) input, key);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	private Object take(List list, Object key) throws Exception
	{
		Integer index = (Integer) ruleToIndex.t(new Object[]{list,key});
		if(index==null) throw new Exception("Element not found inside list with rule: "+key);
		return list.remove(index.intValue());
	}
	
	private Object take(Set set, Object key) throws Exception
	{
		if(!set.contains(key)) throw new Exception("Element not found inside set");
		set.remove(key);
		return key;
	}
	
	private Object take(Map map, Object key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map");
		return map.remove(key);
	}
}
