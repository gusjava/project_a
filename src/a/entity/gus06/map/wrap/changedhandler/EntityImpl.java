package a.entity.gus06.map.wrap.changedhandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.Properties;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200416";}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		P p = (P) o[1];
		
		return new Map1(map,p);
	}
	
	
	private void handle(Map map, P p)
	{
		try{p.p(map);}
		catch(Exception e)
		{Outside.err(this,"handle(Map,P)",e);}
	}
	
	
	
	
	private class Map1 implements Map
	{
		private Map map;
		private P p;
		
		public Map1(Map map, P p)
		{
			this.map = map;
			this.p = p;
		}
		
		public int size() 				{return map.size();}
		public boolean isEmpty() 			{return map.isEmpty();}
		public boolean containsKey(Object key) 		{return map.containsKey(key);}
		public boolean containsValue(Object value) 	{return map.containsValue(value);}
		public Set keySet()				{return map.keySet();}
		public Collection values()			{return map.values();}
		public Set entrySet() 				{return map.entrySet();}
		public Object get(Object key)			{return map.get(key);}
		
		
		public Object put(Object key, Object value)
		{
			Object r = map.put(key,value);
			handle(map,p);
			return r;
		}
		
		public Object remove(Object key)
		{
			Object r = map.remove(key);
			handle(map,p);
			return r;
		}
	
		public void putAll(Map m)
		{
			map.putAll(m);
			handle(map,p);
		}
		
		public void clear()
		{
			map.clear();
			handle(map,p);
		}
	}
}
