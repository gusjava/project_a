package a.entity.gus06.map.wrap.key.support;

import a.framework.*;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20221105";}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Object key = o[1];
		return new Wrap(map,key);
	}
	
	private class Wrap extends S1 implements P, G, R, V, ActionListener
	{
		private Map map;
		private Object key1;
		private Object previous;
		
		public Wrap(Map map, Object key1) throws Exception
		{
			this.map = map;
			this.key1 = key1;
			
			((S)map).addActionListener(this);
			previous = get();
		}
		
		public void actionPerformed(ActionEvent e)
		{
			Object current = get();
			if(Objects.equals(previous,current)) return;
			changed();
			previous = current;
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("map")) {initMap((Map) obj);return;}
			if(key.equals("key")) {initKey(obj);return;}
			
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
		{set(obj);}
		
		public Object g() throws Exception
		{return get();}
		
		private Object get()
		{
			if(!map.containsKey(key1)) return null;
			return map.get(key1);
		}
		
		private void set(Object value) throws Exception
		{
			((S)map).removeActionListener(this);
			if(value==null) map.remove(key1);
			else map.put(key1,value);
			((S)map).addActionListener(this);
		}
		
		private void initMap(Map map_) throws Exception
		{
			if(map!=null) ((S)map).removeActionListener(this);
			map = map_;
			((S)map).addActionListener(this);
			previous = get();
		}
		
		private void initKey(Object key_)
		{
			this.key1 = key_;
			previous = get();
		}
		
		private void changed()
		{send(this,"changed()");}
	}
}
