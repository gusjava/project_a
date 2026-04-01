package a.entity.gus06.appli.dragontale.player.data;

import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, V, R, P {

	public String creationDate() {return "20200516";}


	
	private Service dataInit;

	private Map map;
	private String lastKey;
 	


	public EntityImpl() throws Exception
	{
		dataInit = Outside.service(this,"gus06.appli.dragontale.player.data.init");
		
		map = new HashMap();
		init();
	}



	
	public Object r(String key) throws Exception
	{
		if(key.equals("lastKey")) return lastKey;
		return get(key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{put(key,(String) obj);}



	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("reset")) {reset();return;}
		throw new Exception("Unknown command: "+s);
	}
	
	
	private void reset() throws Exception
	{
		init();
		changed();
	}
	
	
	private void init() throws Exception
	{
		Map defaultMap = (Map) dataInit.g();
		map.clear();
		map.putAll(defaultMap);
	}
	
	
	
	
	private String get(String key)
	{return has(key)?(String) map.get(key):null;}
	
	
	
	private boolean has(String key)
	{return map.containsKey(key);}
	
	
	
	
	private void put(String key, String value)
	{
		value = checkValue(key,value);
		
		String oldValue = get(key);
		if(equals(oldValue,value)) return;
		
		if(value==null) map.remove(key);
		else map.put(key,value);
		
		lastKey = key;
		changed();
	}
	
	
	
	private boolean equals(String v1, String v2)
	{
		if(v1==null && v2==null) return true;
		if(v1==null || v2==null) return false;
		return v1.equals(v2);
	}
	
	
	
	
	private void changed()
	{send(this,"changed()");}
	
	
	
	
	
	
	
	private String checkValue(String key, String value)
	{
		if(value==null) return null;
		
		String key_max = key+"_max";
		String key_min = key+"_min";
		
		if(!has(key_max) && !has(key_min)) return value;
		
		double v = 0;
		try{v = Double.parseDouble(value);}
		catch(NumberFormatException e){return value;}
		
		if(has(key_max))
		{
			double v_max = Double.parseDouble(get(key_max));
			if(v > v_max) v = v_max;
		}
		if(has(key_min))
		{
			double v_min = Double.parseDouble(get(key_min));
			if(v < v_min) v = v_min;
		}
		return ""+v;
	}
}
