package a.entity.gus06.data.perform.get;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170419";}


	private Service ruleToIndex;
	private Service readProp;
	
	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
		readProp = Outside.service(this,"gus06.file.read.properties");
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
		if(input instanceof File) return get((File) input, key);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	private Object get(Object[] array, Object key) throws Exception
	{
		Integer index = (Integer) ruleToIndex.t(new Object[]{array,key});
		if(index==null) return null;
		return array[index.intValue()];
	}
	
	private Object get(List list, Object key) throws Exception
	{
		Integer index = (Integer) ruleToIndex.t(new Object[]{list,key});
		if(index==null) return null;
		return list.get(index.intValue());
	}
	
	private Object get(Map map, Object key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private Object get(File file, Object key) throws Exception
	{
		Map map = (Map) readProp.t(file);
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
