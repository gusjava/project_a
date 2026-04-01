package a.entity.gus06.string.transformfinder.lib.hash;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150926";}
	
	public static final String OFFSET = "hash_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("md2",Outside.service(this,"gus06.string.transform.hash.md2.hexa"));
		put("md5",Outside.service(this,"gus06.string.transform.hash.md5.hexa"));
		put("sha1",Outside.service(this,"gus06.string.transform.hash.sha1.hexa"));
		put("sha256",Outside.service(this,"gus06.string.transform.hash.sha256.hexa"));
		put("sha384",Outside.service(this,"gus06.string.transform.hash.sha384.hexa"));
		put("sha512",Outside.service(this,"gus06.string.transform.hash.sha512.hexa"));
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
