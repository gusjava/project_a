package a.entity.gus06.string.transformfinder.lib.rmchar;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150926";}
	
	public static final String OFFSET = "rmchar_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("diac",Outside.service(this,"gus06.string.transform.character.remove.diacritics"));
		put("digit",Outside.service(this,"gus06.string.transform.character.remove.digit"));
		put("eol",Outside.service(this,"gus06.string.transform.character.remove.eol"));
		put("white",Outside.service(this,"gus06.string.transform.character.remove.whitespace"));
		put("spacetab",Outside.service(this,"gus06.string.transform.character.remove.whitespace.spacetab"));
		put("space",Outside.service(this,"gus06.string.transform.character.remove.whitespace.space"));
		put("tab",Outside.service(this,"gus06.string.transform.character.remove.whitespace.tab"));
		put("letter",Outside.service(this,"gus06.string.transform.character.remove.letter"));
		put("lower",Outside.service(this,"gus06.string.transform.character.remove.lowercase"));
		put("upper",Outside.service(this,"gus06.string.transform.character.remove.uppercase"));
		put("first",Outside.service(this,"gus06.string.transform.character.remove.first"));
		put("last",Outside.service(this,"gus06.string.transform.character.remove.last"));
		put("firstlast",Outside.service(this,"gus06.string.transform.character.remove.firstlast"));
		put("random",Outside.service(this,"gus06.string.transform.character.remove.random"));
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
