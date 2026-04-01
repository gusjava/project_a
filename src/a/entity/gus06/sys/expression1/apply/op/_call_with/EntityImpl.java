package a.entity.gus06.sys.expression1.apply.op._call_with;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170305";}


	private Service builder;
	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.expression1.builder2.t");
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof Map) return new T1(value,opMap);
		if(value instanceof List) return new T1(value,opMap);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		private Map opMap;
		
		public T1(Object data, Map opMap)
		{
			this.data = data;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{return new T2(data, ""+obj, opMap);}
	}
	
	
	private class T2 implements T
	{
		private Object data;
		private String rule;
		private Map opMap;
		
		public T2(Object data, String rule, Map opMap)
		{
			this.data = data;
			this.rule = rule;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{return call(data, rule, obj, opMap);}
	}
	
	
	
	
	
	private Object call(Object data, String rule, Object input, Map opMap) throws Exception
	{
		if(data instanceof Map) return callOnMap((Map) data, rule, (Map) input, opMap);
		if(data instanceof List) return callOnList((List) data, rule, (List) input, opMap);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	private Object callOnMap(Map map, String field, Map input, Map opMap) throws Exception
	{
		if(!map.containsKey(field)) throw new Exception("Field not found: "+field);
		
		Object value = map.get(field);
		T t = (T) builder.t(new Object[]{value,opMap});
		
		Map m = new HashMap(map);
		m.putAll(input);
		
		return t.t(m);
	}
	
	
	private Object callOnList(List list, String rule, List input, Map opMap) throws Exception
	{
		if(list.isEmpty()) throw new Exception("Empty list, not callable");
		Integer n = (Integer) ruleToIndex.t(new Object[]{list,rule});
		if(n==null) throw new Exception("Index not found with rule: "+rule);
		
		Object value = list.get(n.intValue());
		T t = (T) builder.t(new Object[]{value,opMap});
		
		List l = new ArrayList(list);
		l.addAll(0,input);
		
		return t.t(list);
	}
}
