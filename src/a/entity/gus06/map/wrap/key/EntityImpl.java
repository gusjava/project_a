package a.entity.gus06.map.wrap.key;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161215";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key = o[1];
		
		return new Wrap(map,key);
	}
	
	
	private class Wrap implements P, G, R, V
	{
		private Map map;
		private Object key1;
		
		public Wrap(Map map, Object key1)
		{
			this.map = map;
			this.key1 = key1;
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("map")) {map = (Map) obj;return;}
			if(key.equals("key")) {key1 = obj;return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("map")) return map;
			if(key.equals("key")) return key1;
			
			if(key.equals("keys")) return new String[]{"map","key"};
			throw new Exception("Unknown key: "+key);
		}
		
		public void p(Object obj) throws Exception
		{
			if(obj==null) map.remove(key1);
			else map.put(key1,obj);
		}
		
		public Object g() throws Exception
		{
			if(!map.containsKey(key1)) return null;
			return map.get(key1);
		}
	}
}
