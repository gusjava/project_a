package a.entity.gus06.string.transform.format.character.special.byname;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20231107";}
	
	private Map map;
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("sqrt","\u221a");
		put("cbrt","\u221b");
		
		put("sqr","\u00b2");
		put("cube","\u00b3");
		
		put("arobas","@");
		put("euro","€");
		put("dollar","$");
		put("percent","%");
		
		put("copyright","\u00a9");
		put("(c)","\u00a9");
		
		put("different","\u2260");
		put("<>","\u2260");
		
		put("belongs","\u2208");
		put("belongs_not","\u2209");
		
		
		put("oe","\u0153");
		
		put("ae","\u00e6");
		
		put("left","\u2190");
		put("<-","\u2190");
		
		put("right","\u2192");
		put("->","\u2192");
		
		put("up","\u2191");
		put("down","\u2193");
		
		put("<=","\u21d0");
		put("=>","\u21d2");
		put("<=>","\u21d4");
		
		put("watch","\u231a");
	}
	
	
	private void put(String key, String value) throws Exception
	{map.put(key,value);}
	
	
	public Object g() throws Exception
	{return map;}


	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(map.containsKey(s)) return map.get(s);
		return "("+s+":NOT FOUND)";
	}
}