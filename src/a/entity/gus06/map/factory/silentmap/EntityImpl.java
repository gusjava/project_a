package a.entity.gus06.map.factory.silentmap;

import a.framework.*;
import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20191215";}
	
	
	public Object g() throws Exception
	{return new Map1();}
	
	
	private class Map1 implements Map
	{
		private Map map = new HashMap();
		
		public int size() {return map.size();}
		public boolean isEmpty(){return map.isEmpty();}
		public boolean containsKey(Object key){return map.containsKey(key);}
		public boolean containsValue(Object value){return map.containsValue(value);}
		public Set keySet(){return map.keySet();}
		public Collection values(){return map.values();}
		public Set entrySet(){return map.entrySet();}
		public Object get(Object key){return map.get(key);}
		public Object put(Object key, Object value){return map.put(key,value);}
		public Object remove(Object key){return map.remove(key);}
		public void putAll(Map m){map.putAll(m);}
		public void clear(){map.clear();}
		public String toString(){return "Map1";}
	}
}
