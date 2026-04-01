package a.entity.gus06.string.transformfinder.lib.jap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150927";}
	
	public static final String OFFSET = "jap_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("romaji_to_hiragana",Outside.service(this,"gus06.string.transform.japanese.hiragana.builder"));
		put("hiragana_to_romaji",Outside.service(this,"gus06.string.transform.japanese.hiragana.convertor"));
		put("romaji_to_katakana",Outside.service(this,"gus06.string.transform.japanese.katakana.builder"));
		put("katakana_to_romaji",Outside.service(this,"gus06.string.transform.japanese.katakana.convertor"));
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
