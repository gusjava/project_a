package a.entity.gus06.string.transformfinder.lib.norm;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150926";}
	
	public static final String OFFSET = "norm_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("diac",Outside.service(this,"gus06.string.transform.normalize.diacritics"));
		put("diac_lower",Outside.service(this,"gus06.string.transform.normalize.diacritics.lower"));
		put("diac_upper",Outside.service(this,"gus06.string.transform.normalize.diacritics.upper"));
		put("filename",Outside.service(this,"gus06.string.transform.normalize.filename"));
		put("whitespace",Outside.service(this,"gus06.string.transform.normalize.whitespace"));
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