package a.entity.gus06.sys.expression1.apply.op._call1_with;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170305";}
	
	public static final String FIELD = "output";


	private Service builder;
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.expression1.builder2.t");
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
		{return call(data, obj, opMap);}
	}
	
	
	
	
	private Object call(Object data, Object input, Map opMap) throws Exception
	{
		if(data instanceof Map) return callOnMap((Map) data, (Map) input, opMap);
		if(data instanceof List) return callOnList((List) data, (List) input, opMap);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	private Object callOnMap(Map map, Map input, Map opMap) throws Exception
	{
		if(!map.containsKey(FIELD)) throw new Exception("Field not found: "+FIELD);
		
		Object value = map.get(FIELD);
		T t = (T) builder.t(new Object[]{value,opMap});
		
		Map m = new HashMap(map);
		m.putAll(input);
		
		return t.t(m);
	}
	
	
	private Object callOnList(List list, List input, Map opMap) throws Exception
	{
		if(list.isEmpty()) throw new Exception("Empty list, not callable");
		
		Object value = list.get(list.size()-1);
		T t = (T) builder.t(new Object[]{value,opMap});
		
		List l = new ArrayList(list);
		l.addAll(0,input);
		
		return t.t(list);
	}
}
