package a.entity.gus06.data.perform.in;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160905";}


	private Service fromRule1;
	
	public EntityImpl() throws Exception
	{
		fromRule1 = Outside.service(this,"gus06.data.filter.number.fromrule1");
	}

	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Object struct = o[1];
		
		if(struct instanceof List) return inList(data,(List) struct);
		if(struct instanceof Set) return inSet(data,(Set) struct);
		if(struct instanceof Map) return inMap(data,(Map) struct);
		if(struct instanceof String) return inString(data,(String) struct);
		
		if(struct instanceof Object[]) return inArray(data,(Object[]) struct);
		if(struct instanceof int[]) return inArray(data,(int[]) struct);
		if(struct instanceof long[]) return inArray(data,(long[]) struct);
		if(struct instanceof double[]) return inArray(data,(double[]) struct);
		if(struct instanceof float[]) return inArray(data,(float[]) struct);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private boolean inList(Object data, List list)
	{
		return list.contains(data);
	}
	
	private boolean inSet(Object data, Set set)
	{
		return set.contains(data);
	}
	
	private boolean inMap(Object data, Map map)
	{
		return map.containsKey(data);
	}
	
	private boolean inString(Object data, String s) throws Exception
	{
		if(data instanceof String) return s.contains((String) data);
		if(data instanceof Number) return fromRule1.f(new Object[]{data,s});
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	private boolean inArray(Object data, Object[] array)
	{
		for(Object elem:array)
		if(data.equals(elem)) return true;
		return false;
	}
	
	private boolean inArray(Object data, int[] array)
	{
		if(!(data instanceof Integer)) return false;
		Integer number = (Integer) data;
		
		for(int elem:array)
		if(number.intValue()==elem) return true;
		return false;
	}
	
	private boolean inArray(Object data, long[] array)
	{
		if(!(data instanceof Long)) return false;
		Long number = (Long) data;
		
		for(long elem:array)
		if(number.longValue()==elem) return true;
		return false;
	}
	
	private boolean inArray(Object data, double[] array)
	{
		if(!(data instanceof Double)) return false;
		Double number = (Double) data;
		
		for(double elem:array)
		if(number.doubleValue()==elem) return true;
		return false;
	}
	
	private boolean inArray(Object data, float[] array)
	{
		if(!(data instanceof Float)) return false;
		Float number = (Float) data;
		
		for(float elem:array)
		if(number.floatValue()==elem) return true;
		return false;
	}
}
