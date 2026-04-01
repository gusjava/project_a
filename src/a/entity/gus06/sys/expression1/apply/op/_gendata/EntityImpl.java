package a.entity.gus06.sys.expression1.apply.op._gendata;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220618";}


	private Service builderT;
	private Service subMap;
	
	public EntityImpl() throws Exception
	{
		builderT = Outside.service(this,"gus06.sys.expression1.builder2.t");
		subMap = Outside.service(this,"gus06.map.string.submap");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof Map) return generate((Map) value, opMap);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	private Map generate(Map m, Map opMap) throws Exception
	{
		Map dataMap = buildDataMap(m);
		
		Map dataMap1 = new HashMap();
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Object value = m.get(key);
			
			if(value!=null && value instanceof Map)
			{
				Object data = handleMap((Map) value, dataMap, opMap);
				if(data!=null) dataMap1.put(key,data);
			}
		}
		
		dataMap.putAll(dataMap1);
		return dataMap;
	}
	
	
	
	
	
	
	private Map buildDataMap(Map m) throws Exception
	{
		Map dataMap = new HashMap();
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Object value = m.get(key);
			
			Object data = handleObject(value);
			if(data!=null) dataMap.put(key,data);
		}
		return dataMap;
	}
	
	private Object handleObject(Object value) throws Exception
	{
		if(value==null) return null;
		if(value instanceof List) return random((List) value);
		if(value instanceof G) return ((G) value).g();
		if(value instanceof Map) return null;
		return value;
	}
	
	private Object random(List list)
	{
		int index = random(list.size());
		return list.get(index);
	}
	
	private int random(int limit)
	{return (int) (Math.random()*limit);}
	
	
	
	
	
	
	
	
	
	
	
	private Object handleMap(Map value, Map dataMap , Map opMap) throws Exception
	{
		Map m0 = (Map) subMap.t(new Object[]{value,"data_"});
		Map d0 = buildDataMap(m0);
		
		Map dataMap0 = new HashMap();
		dataMap0.putAll(dataMap);
		dataMap0.putAll(d0);
		
		String rule = (String) get1(value, "rule");
		Object data = handleRule(rule, dataMap0, opMap);
		
		return data;
	}
	
	private Object handleRule(Object rule, Map dataMap, Map opMap) throws Exception
	{
		T t = (T) builderT.t(new Object[]{rule,opMap});
		return t.t(dataMap);
	}
	
	private Object get1(Map m, String key) throws Exception
	{
		if(!m.containsKey(key)) throw new Exception("Unknown key: "+key);
		return m.get(key);
	}
}