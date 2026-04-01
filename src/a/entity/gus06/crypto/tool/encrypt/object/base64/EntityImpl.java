package a.entity.gus06.crypto.tool.encrypt.object.base64;

import a.framework.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200416";}


	private Service handleString;

	public EntityImpl() throws Exception
	{
		handleString = Outside.service(this,"gus06.crypto.tool.encrypt.string.base64");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return handleObject((T) o[0],o[1]);
	}
	
	
	private Object handleObject(T t, Object data) throws Exception
	{
		if(data instanceof byte[]) return t.t(data);
		if(data instanceof String) return handleString.t(new Object[]{t,data});
		if(data instanceof Map) return handleMap(t,(Map) data);
		if(data instanceof List) return handleList(t,(List) data);
		if(data instanceof Set) return handleSet(t,(Set) data);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}

	private Map handleMap(T t, Map map) throws Exception
	{
		Map map1 = new HashMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = map.get(key);
			
			Object key1 = handleObject(t,key);
			Object value1 = handleObject(t,value);
			
			map1.put(key1,value1);
		}
		return map1;
	}

	private List handleList(T t, List list) throws Exception
	{
		List list1 = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			Object value = list.get(i);
			Object value1 = handleObject(t,value);
			list1.add(value1);
		}
		return list1;
	}

	private Set handleSet(T t, Set set) throws Exception
	{
		Set set1 = new HashSet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			Object value = it.next();
			Object value1 = handleObject(t,value);
			set1.add(value1);
		}
		return set1;
	}
}
