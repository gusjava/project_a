package a.entity.gus06.map.string.valueholder;

import a.framework.*;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150713";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map) return new Holder((Map)obj,null);
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return new Holder((Map)o[0],(String)o[1]);
	}
	
	
	
	private class Holder extends S1 implements P, G, R, V, F, ActionListener
	{
		private Map m;
		private String k;
		private String v;
		
		
		public Holder(Map map, String key) throws Exception
		{
			initMap(map);
			initKey(key);
		}
		
		
		
		private void initMap(Map map) throws Exception
		{
			if(m!=null) removeActionListener();
			m = map;
			if(m==null) throw new Exception("Invalid null value for map");
			addActionListener();
		}
		
		private void initKey(String key)
		{
			k = key;
			v = get();
		}
		
		
		
		private String get()
		{
			if(k==null) return null;
			return m.containsKey(k)?(String) m.get(k):null;
		}
		
		private void set(String value) throws Exception
		{
			if(k==null) return;
			v = value;
			
			removeActionListener();
			if(v==null) m.remove(k);
			else m.put(k,v);
			addActionListener();
		}
		
		
		
		private void addActionListener() throws Exception
		{if(m instanceof S)((S)m).addActionListener(this);}
		
		private void removeActionListener() throws Exception
		{if(m instanceof S)((S)m).removeActionListener(this);}
		
		
		
		public void actionPerformed(ActionEvent e)
		{
			String v1 = get();
			if(equals(v,v1)) return;
			v = v1;
			modified();
		}
		
		
		private void modified()
		{send(this,"modified()");}
		
		
		private boolean equals(Object o1, Object o2)
		{
			if(o1==null && o2==null) return true;
			if(o1==null || o2==null) return false;
			return o1.equals(o2);
		}
		
		
		public Object g() throws Exception
		{return get();}
		
		public void p(Object obj) throws Exception
		{set((String)obj);}
		
		public boolean f(Object obj) throws Exception
		{return get()!=null;}
		
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("key")) return k;
			if(key.equals("map")) return m;
			if(key.equals("keys")) return new String[]{"key","map"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("key")) {initKey((String) obj);return;}
			
			throw new Exception("Unknown key: "+key);
		}
	}
}
