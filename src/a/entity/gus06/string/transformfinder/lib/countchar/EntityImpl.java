package a.entity.gus06.string.transformfinder.lib.countchar;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150927";}
	
	public static final String OFFSET = "countchar_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("diac",Outside.service(this,"gus06.string.transform.character.count.diacritics"));
		put("digit",Outside.service(this,"gus06.string.transform.character.count.digit"));
		put("letter",Outside.service(this,"gus06.string.transform.character.count.letter"));
		put("distinct",Outside.service(this,"gus06.string.transform.character.count.distinct"));
		put("lower",Outside.service(this,"gus06.string.transform.character.count.lowercase"));
		put("upper",Outside.service(this,"gus06.string.transform.character.count.uppercase"));
		put("white",Outside.service(this,"gus06.string.transform.character.count.whitespace"));
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
