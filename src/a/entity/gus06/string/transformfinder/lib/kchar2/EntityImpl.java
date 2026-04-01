package a.entity.gus06.string.transformfinder.lib.kchar2;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20160502";}
	
	public static final String OFFSET = "kchar2_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("digit",Outside.service(this,"gus06.string.transform.character.keep2.digit"));
		put("hiragana",Outside.service(this,"gus06.string.transform.character.keep2.hiragana"));
		put("jap",Outside.service(this,"gus06.string.transform.character.keep2.japanese"));
		put("kana",Outside.service(this,"gus06.string.transform.character.keep2.kana"));
		put("kanji",Outside.service(this,"gus06.string.transform.character.keep2.kanji"));
		put("katakana",Outside.service(this,"gus06.string.transform.character.keep2.katakana"));
		put("letter",Outside.service(this,"gus06.string.transform.character.keep2.letter"));
		put("lower",Outside.service(this,"gus06.string.transform.character.keep2.lowercase"));
		put("upper",Outside.service(this,"gus06.string.transform.character.keep2.uppercase"));
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