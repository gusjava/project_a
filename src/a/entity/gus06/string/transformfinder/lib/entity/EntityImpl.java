package a.entity.gus06.string.transformfinder.lib.entity;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20151024";}
	
	public static final String OFFSET = "entity_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("co",Outside.service(this,"gus06.string.transform.app.entity.listing.co"));
		put("en",Outside.service(this,"gus06.string.transform.app.entity.listing.en"));
		put("st",Outside.service(this,"gus06.string.transform.app.entity.listing.st"));
		put("srccode",Outside.service(this,"gus06.string.transform.app.entity.srccode"));
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
