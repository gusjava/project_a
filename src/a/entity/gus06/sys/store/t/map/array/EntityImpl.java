package a.entity.gus06.sys.store.t.map.array;

import a.framework.*;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140929";}


	private Service findObj;
	
	public static final String KEY_COUNT = "count";



	public EntityImpl() throws Exception
	{
		findObj = Outside.service(this,"gus06.sys.store.obj.find");
	}

	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		int count = int_(get(map,KEY_COUNT));
		
		Object[] array = new Object[count];
		
		for(int i=0;i<count;i++)
		array[i] = findObj(get(map,"element-"+i));
		
		return array;
	}
	
	
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return (String) map.get(key);
	}
	
	
	private int int_(String s)
	{return Integer.parseInt(s);}
	
	
	private Object findObj(String rule) throws Exception
	{return findObj.t(rule);}
	
}
