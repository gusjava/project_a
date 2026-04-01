package a.entity.gus06.map.map1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150910";}

	
	
	public Object t(Object obj) throws Exception
	{return new Map1((String) obj);}
	
	
	
	
	private class Map1 implements Map
	{
		private Map m;
		private String name;
		
		public Map1(String name)
		{
			this.name = name;
			m = new HashMap();
		}
		
		public int size()
		{return m.size();}
		
		public boolean isEmpty()
		{return m.isEmpty();}
		
		public boolean containsKey(Object key)
		{return m.containsKey(key);}
		
		public boolean containsValue(Object value)
		{return m.containsValue(value);}
		
		public Object get(Object key)
		{return m.get(key);}
		
		public Object put(Object key, Object value)
		{return m.put(key,value);}
		
		public Object remove(Object key)
		{return m.remove(key);}
		
		public void putAll(Map map)
		{m.putAll(map);}
		
		public void clear()
		{m.clear();}
		
		public Set keySet()
		{return m.keySet();}
		
		public Collection values()
		{return m.values();}
		
		public Set entrySet()
		{return m.entrySet();}
		
		public String toString()
		{return name;}
	}
}
