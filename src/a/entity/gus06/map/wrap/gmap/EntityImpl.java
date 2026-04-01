package a.entity.gus06.map.wrap.gmap;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20160301";}
	
	
	public Object t(Object obj) throws Exception
	{return new Map1((Map) obj);}
	
	public Object g() throws Exception
	{return new Map1(new HashMap());}
	
	
	
	private class Map1 implements Map
	{
		private Map map;
		
		public Map1(Map map)
		{this.map = map;}
		
		public int size() 				{return map.size();}
		public boolean isEmpty() 			{return map.isEmpty();}
		public boolean containsKey(Object key) 		{return map.containsKey(key);}
		public boolean containsValue(Object value) 	{return map.containsValue(value);}
		public Set keySet()				{return map.keySet();}
		public Collection values()			{return map.values();}
		public Set entrySet() 				{return map.entrySet();}
		public Object get(Object key)			{return m(map.get(key));}
		public boolean equals(Object o)			{return map.equals(o);}
	
		public Object put(Object key, Object value)	{return map.put(key,value);}
		public Object remove(Object key)		{return map.remove(key);}
		public void putAll(Map m)			{map.putAll(m);}
		public void clear()				{map.clear();}
	}
	
	
	
	private Object m(Object obj)
	{
		try
		{
			if(obj instanceof G)
			return ((G) obj).g();
		}
		catch(Exception e)
		{Outside.err(this,"m(Object)",e);}
		return obj;
	}
}
