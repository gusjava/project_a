package a.entity.gus06.map.build.keyaccess;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20161010";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map)
			return new Holder((Map) obj,null);
		if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			return new Holder((Map) o[0],o[1]);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	public Object g() throws Exception
	{return new Holder(null,null);}
	
	
	
	private class Holder extends S1 implements R, V, P, G, F
	{
		private Map map;
		private Object key1;
		
		public Holder(Map map, Object key1)
		{
			this.map = map;
			this.key1 = key1;
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("map")) return map;
			if(key.equals("key")) return key1;
			if(key.equals("keys")) return new String[]{"map","key"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("map"))
			{
				map = (Map) obj;
				reset();
				return;
			}
			if(key.equals("key"))
			{
				key1 = obj;
				reset();
				return;
			}
			throw new Exception("Unknown key: "+key);
		}
		
		public Object g() throws Exception
		{
			if(map==null) throw new Exception("Map not initialized yet");
			if(key1==null) throw new Exception("Key not initialized yet");
			
			if(!map.containsKey(key1)) return null;
			return map.get(key1);
		}
		
		public void p(Object obj) throws Exception
		{
			if(map==null) throw new Exception("Map not initialized yet");
			if(key1==null) throw new Exception("Key not initialized yet");
			
			map.put(key1,obj);
			modified();
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(map==null) throw new Exception("Map not initialized yet");
			if(key1==null) throw new Exception("Key not initialized yet");
			
			if(!map.containsKey(key1)) return obj==null;
			return map.get(key1).equals(obj);
		}
		
		private void modified()
		{send(this,"modified()");}
		
		private void reset()
		{send(this,"reset()");}
	}
}
