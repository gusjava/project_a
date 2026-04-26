package a.entity.gus06.string.transformfinder.lib.str;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150926";}
	
	public static final String OFFSET = "str_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("double",Outside.service(this,"gus06.string.transform.str.double1"));
		put("empty",Outside.service(this,"gus06.string.transform.str.empty"));
		put("length",Outside.service(this,"gus06.string.transform.str.length"));
		put("lower",Outside.service(this,"gus06.string.transform.str.lower"));
		put("now",Outside.service(this,"gus06.string.transform.str.now"));
		put("same",Outside.service(this,"gus06.string.transform.str.same"));
		put("spacing",Outside.service(this,"gus06.string.transform.str.spacing"));
		put("titled",Outside.service(this,"gus06.string.transform.str.titled"));
		put("titled_inv",Outside.service(this,"gus06.string.transform.str.titled.inv"));
		put("trim",Outside.service(this,"gus06.string.transform.str.trim"));
		put("trimst",Outside.service(this,"gus.x.transform.string.trim.start"));
		put("trimen",Outside.service(this,"gus06.string.transform.str.trim.end"));
		put("trimnw",Outside.service(this,"gus06.string.transform.str.trim.ifnotwhite"));
		put("trimw",Outside.service(this,"gus06.string.transform.str.trim.ifwhite"));
		put("triple",Outside.service(this,"gus06.string.transform.str.triple"));
		put("upper",Outside.service(this,"gus06.string.transform.str.upper"));
		put("whiten",Outside.service(this,"gus06.string.transform.str.whiten"));
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
