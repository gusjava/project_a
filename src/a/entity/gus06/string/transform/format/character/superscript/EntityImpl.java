package a.entity.gus06.string.transform.format.character.superscript;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20231104";}
	
	
	private Map map;
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("a","\u00aa");
		put("o","\u00b0");
		put("s","\u02e2");
		put("x","\u02e3");
		
		put("0","\u00ba");
		put("1","\u00b9");
		put("2","\u00b2");
		put("3","\u00b3");
	}
	
	
	private void put(String key, String value)
	{map.put(key,value);}
	
	
	public Object g() throws Exception
	{return map;}


	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) map.get(key);
			s = s.replace(key, value);
		}
		return s;
	}
}