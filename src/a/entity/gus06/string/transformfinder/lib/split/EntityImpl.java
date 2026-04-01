package a.entity.gus06.string.transformfinder.lib.split;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150927";}
	
	public static final String OFFSET = "split_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("chars_to_lines",Outside.service(this,"gus06.string.transform.split.chars.tolines"));
		put("chars_to_seq",Outside.service(this,"gus06.string.transform.split.chars.tosequence"));
		put("lines_to_seq",Outside.service(this,"gus06.string.transform.split.lines.tosequence"));
		put("words_to_lines",Outside.service(this,"gus06.string.transform.split.words.tolines"));
		put("words_to_seq",Outside.service(this,"gus06.string.transform.split.words.tosequence"));
		
		put("len10_to_lines",Outside.service(this,"gus06.string.transform.split.length10.tolines"));
		put("len10_to_seq",Outside.service(this,"gus06.string.transform.split.length10.tosequence"));
		//...
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
