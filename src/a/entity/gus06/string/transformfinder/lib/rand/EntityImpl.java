package a.entity.gus06.string.transformfinder.lib.rand;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150926";}
	
	public static final String OFFSET = "rand_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("alphanum_len",Outside.service(this,"gus06.string.transform.random.alphanum.length"));
		put("num_len",Outside.service(this,"gus06.string.transform.random.number.length"));
		put("num",Outside.service(this,"gus06.string.transform.random.number.limit"));
		put("chance",Outside.service(this,"gus06.string.transform.random.chance.limit"));
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
