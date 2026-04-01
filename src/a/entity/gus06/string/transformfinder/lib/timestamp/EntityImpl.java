package a.entity.gus06.string.transformfinder.lib.timestamp;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150926";}
	
	public static final String OFFSET = "timestamp_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("age",Outside.service(this,"gus06.string.transform.format.timestamp.age"));
		put("aslong",Outside.service(this,"gus06.string.transform.format.timestamp.aslong"));
		put("yyyymm_fr",Outside.service(this,"gus06.string.transform.format.timestamp.fr1"));
		put("yyyymmdd_fr",Outside.service(this,"gus06.string.transform.format.timestamp.fr2"));
		put("yyyymmddhhmm_fr",Outside.service(this,"gus06.string.transform.format.timestamp.fr3"));
		put("yyyymmddhhmmss_fr",Outside.service(this,"gus06.string.transform.format.timestamp.fr4"));
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
