package a.entity.gus06.map.build.fromlist.analyzeelem;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170327";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof double[])
		{
			double[] array = (double[]) obj;
			if(array.length!=2) throw new Exception("Invalid element array length: "+array.length);
			return new Object[]{Double.valueOf(array[0]),Double.valueOf(array[1])};
		}
		if(obj instanceof int[])
		{
			int[] array = (int[]) obj;
			if(array.length!=2) throw new Exception("Invalid element array length: "+array.length);
			return new Object[]{Integer.valueOf(array[0]),Integer.valueOf(array[1])};
		}
		if(obj instanceof long[])
		{
			long[] array = (long[]) obj;
			if(array.length!=2) throw new Exception("Invalid element array length: "+array.length);
			return new Object[]{Long.valueOf(array[0]),Long.valueOf(array[1])};
		}
		if(obj instanceof Object[])
		{
			Object[] array = (Object[]) obj;
			if(array.length!=2) throw new Exception("Invalid element array length: "+array.length);
			return array;
		}
		if(obj instanceof List)
		{
			List list = (List) obj;
			if(list.size()!=2) throw new Exception("Invalid element list size: "+list.size());
			return new Object[]{list.get(0),list.get(1)};
		}
		if(obj instanceof Map)
		{
			Map map = (Map) obj;
			if(map.size()!=2) throw new Exception("Invalid element map size: "+map.size());
			Object key = get(map,"key");
			Object value = get(map,"value");
			return new Object[]{key,value};
		}
		if(obj instanceof String)
		{
			String s = (String) obj;
			String delim = findDelim(s);
			String[] n = s.split(delim,2);
			if(n.length!=2) throw new Exception("Invalid element map text: "+s);
			String key = n[0].trim();
			String value = n[1].trim();
			return new Object[]{key,value};
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
	
	
	private String findDelim(String s) throws Exception
	{
		if(s.contains("=")) return "=";
		if(s.contains(":")) return ":";
		if(s.contains("\t")) return "\t";
		throw new Exception("Invalid text: "+s);
	}
}
