package a.entity.gus06.data.perform.init;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200402";}


	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
		
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Object key = o[1];
		Object initValue = o[2];
		
		if(data instanceof Map)
			return init((Map) data, key, initValue);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	private Object init(Map map, Object key, Object initValue)
	{
		if(!map.containsKey(key)) map.put(key,initValue);
		return map.get(key);
	}
}
