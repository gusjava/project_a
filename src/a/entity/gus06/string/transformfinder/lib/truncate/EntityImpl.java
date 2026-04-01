package a.entity.gus06.string.transformfinder.lib.truncate;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150926";}
	
	public static final String OFFSET = "truncate_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("5",Outside.service(this,"gus06.string.transform.truncate.length5"));
		put("10",Outside.service(this,"gus06.string.transform.truncate.length10"));
		put("20",Outside.service(this,"gus06.string.transform.truncate.length20"));
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(map.containsKey(s)) return map.get(s);
		return null;
	}
	
	public Object g() throws Exception
	{return map;}
}
