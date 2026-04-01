package a.entity.gus06.string.transformfinder.lib.decode;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150926";}
	
	public static final String OFFSET = "decode_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("datastring",Outside.service(this,"gus06.string.transform.encoding.datastring.decode"));
		put("gouvernail",Outside.service(this,"gus06.string.transform.encoding.gouvernail.decode"));
		put("hill",Outside.service(this,"gus06.string.transform.encoding.hill.decode"));
		put("url",Outside.service(this,"gus06.string.transform.encoding.url.decode"));
		put("uu",Outside.service(this,"gus06.string.transform.encoding.uu.decode"));
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
