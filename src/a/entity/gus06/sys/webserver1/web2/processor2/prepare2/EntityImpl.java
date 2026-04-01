package a.entity.gus06.sys.webserver1.web2.processor2.prepare2;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141001";}
	
	
	
	public Object t(Object obj) throws Exception
	{return new MainHolder((Map) obj);}
	
	
	
	private class MainHolder implements R, V, G, F
	{
		private Map main;
		public MainHolder(Map main)
		{this.main = main;}
		
		public Object g() throws Exception
		{return main;}
		
		public Object r(String key) throws Exception
		{
			Object m = main;
			String[] n = key.split(" ");
			for(String s:n) 
			{
				if(m==null) throw new Exception("Invalid retrieve key: "+key);
				else if(m instanceof Map) m = get_((Map)m,s);
				else if(m instanceof R) m = ((R)m).r(s);
				else throw new Exception("Invalid retrieve key: "+key);
			}
			return m;
		}
		
		public void v(String key, Object obj) throws Exception
		{
			Object m = main;
			String[] n = key.split(" ");
			for(int i=0;i<n.length;i++)
			{
				String s = n[i];
				if(i<n.length-1)
				{
					if(m instanceof Map) m = get_((Map)m,s);
					else if(m instanceof R) m = ((R)m).r(s);
					else throw new Exception("Invalid store key: "+key);
				}
				else
				{
					if(m instanceof Map) ((Map)m).put(s,obj);
					else if(m instanceof V) ((V)m).v(s,obj);
					else throw new Exception("Invalid store key: "+key);
				}
			}
		}
		
		public boolean f(Object obj) throws Exception
		{
			Object m = main;
			String key = (String) obj;
			String[] n = key.split(" ");
			for(String s:n) 
			{
				if(m==null) return false;
				if(m instanceof Map) m = get((Map)m,s);
				else if(m instanceof R) m = ((R)m).r(s);
				else throw new Exception("Invalid store key: "+key);
			}
			return m!=null;
		}
	}
	
	
	
	
	
	private Object get_(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
