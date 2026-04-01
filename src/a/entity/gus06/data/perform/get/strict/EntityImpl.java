package a.entity.gus06.data.perform.get.strict;

import a.framework.*;
import java.util.List;
import java.util.Map;

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
		
		if(input instanceof Object[]) return get((Object[]) input, key);
		if(input instanceof List) return get((List) input, key);
		if(input instanceof Map) return get((Map) input, key);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	private Object get(Object[] array, Object key) throws Exception
	{
		Integer index = (Integer) ruleToIndex.t(new Object[]{array,key});
		if(index==null) throw new Exception("Element not found inside array with rule: "+key);
		return array[index.intValue()];
	}
	
	private Object get(List list, Object key) throws Exception
	{
		Integer index = (Integer) ruleToIndex.t(new Object[]{list,key});
		if(index==null) throw new Exception("Element not found inside list with rule: "+key);
		return list.get(index.intValue());
	}
	
	private Object get(Map map, Object key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map");
		return map.get(key);
	}
}