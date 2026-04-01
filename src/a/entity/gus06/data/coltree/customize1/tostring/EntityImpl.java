package a.entity.gus06.data.coltree.customize1.tostring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150609";}


	public Object t(Object obj) throws Exception
	{return buildObject("root",obj);}

	
	private Object buildObject(String name, Object obj)
	{
		if(obj instanceof String) return name+":"+obj;
		if(obj instanceof Map) return buildMap(name,(Map) obj);
		if(obj instanceof List) return buildList(name,(List) obj);
		if(obj instanceof Object[]) return buildArray(name,(Object[]) obj);
		
		return obj;
	}
	
	
	
	private Map buildMap(String name, Map map)
	{
		Map map1 = new HashMap1(name);
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Object value = map.get(key);
			map1.put(key,buildObject(key,value));
		}
		return map1;
	}
	
	
	
	private List buildList(String name, List list)
	{
		List list1 = new ArrayList0(name);
		for(int i=0;i<list.size();i++)
		{
			Object value = list.get(i);
			list1.add(buildObject(""+i,value));
		}
		return list1;
	}
	
	
	
	private Object[] buildArray(String name, Object[] array)
	{
		Object[] array1 = new Object[array.length];
		for(int i=0;i<array.length;i++)
		array1[i] = buildObject(""+i,array[i]);
		return array1;
	}
	
	
	
	
	
	
	
	
	
	public class HashMap1 extends HashMap
	{
		private String name;
		public HashMap1(String name) {this.name = name;}
		public String toString() {return name;}
	}
	
	public class HashSet0 extends HashSet
	{
		private String name;
		public HashSet0(String name) {this.name = name;}
		public String toString() {return name;}
	}
	
	public class ArrayList0 extends ArrayList
	{
		private String name;
		public ArrayList0(String name) {this.name = name;}
		public String toString() {return name;}
	}
}
