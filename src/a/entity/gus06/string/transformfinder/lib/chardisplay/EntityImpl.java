package a.entity.gus06.string.transformfinder.lib.chardisplay;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20160502";}
	
	public static final String OFFSET = "chardisplay_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("codepoint",Outside.service(this,"gus06.string.transform.character.display.codepoint"));
		put("intvalue",Outside.service(this,"gus06.string.transform.character.display.intvalue"));
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
