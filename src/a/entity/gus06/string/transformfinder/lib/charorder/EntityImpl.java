package a.entity.gus06.string.transformfinder.lib.charorder;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150926";}
	
	public static final String OFFSET = "charorder_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("invert",Outside.service(this,"gus06.string.transform.character.order.invert"));
		put("permute",Outside.service(this,"gus06.string.transform.character.order.permute"));
		put("permuteinv",Outside.service(this,"gus06.string.transform.character.order.permute.inv"));
		put("shuffle",Outside.service(this,"gus06.string.transform.character.order.shuffle"));
		put("sort",Outside.service(this,"gus06.string.transform.character.order.sort"));
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
