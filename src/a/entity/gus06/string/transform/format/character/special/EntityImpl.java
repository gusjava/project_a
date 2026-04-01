package a.entity.gus06.string.transform.format.character.special;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20160503";}
	
	public final static int LENMAX = 30;
	
	private Map[] maps;
	private Map fullMap;
	
	public EntityImpl() throws Exception
	{
		maps = new Map[LENMAX];
		for(int i=0;i<LENMAX;i++) maps[i] = new HashMap();
		fullMap = new HashMap();
		
		put("<->","\u2194");
		put("<-","\u2190");
		put("->","\u2192");
		
		put("km2","km\u00B2");
		put("dm2","dm\u00B2");
		put("cm2","cm\u00B2");
		put("mm2","mm\u00B2");
		put("m2","m\u00B2");
		
		put("km3","km\u00B3");
		put("dm3","dm\u00B3");
		put("cm3","cm\u00B3");
		put("mm3","mm\u00B3");
		put("m3","m\u00B3");
		
		put("<=>","\u21d4");
		put("<=","\u21d0");
		put("=>","\u21d2");
		
		put("<<","\u00ab");
		put(">>","\u00bb");
		
		put("(c)","\u00A9");
		put("(P)","\u2117");
		put("OE","\u0152");
		put("oe","\u0153");
		put("AE","\u00c6");
		put("ae","\u00e6");
		
		put("pi","\u03c0");
		
		put("1/4","\u00bc");
		put("1/2","\u00bd");
		put("3/4","\u00be");
		
		put("1/10","\u2152");
		put("1/3","\u2153");
		put("2/3","\u2154");
		put("1/5","\u2155");
		put("2/5","\u2156");
		put("3/5","\u2157");
		put("4/5","\u2158");
		put("1/6","\u2159");
		put("5/6","\u215a");
		put("1/8","\u215b");
		put("3/8","\u215c");
		put("5/8","\u215d");
		put("7/8","\u215e");
		
		put("[]","\u2751");
		put("*","\u2022");
		
		put("(1)","\u2780");
		put("(2)","\u2781");
		put("(3)","\u2782");
		put("(4)","\u2783");
		put("(5)","\u2784");
		put("(6)","\u2785");
		put("(7)","\u2786");
		put("(8)","\u2787");
		put("(9)","\u2788");
		put("(10)","\u2789");
		
		put(":)","\uD83D\uDE01");
		put(":D","\uD83D\uDE02");
		
		put("\\uD83D\\uDE00","\uD83D\uDE00");
		put("\\uD83D\\uDE01","\uD83D\uDE01");
		put("\\uD83D\\uDE02","\uD83D\uDE02");
		put("\\uD83D\\uDE03","\uD83D\uDE03");
		put("\\uD83D\\uDE04","\uD83D\uDE04");
		put("\\uD83D\\uDE05","\uD83D\uDE05");
		put("\\uD83D\\uDE06","\uD83D\uDE06");
		put("\\uD83D\\uDE07","\uD83D\uDE07");
		put("\\uD83D\\uDE08","\uD83D\uDE08");
		put("\\uD83D\\uDE09","\uD83D\uDE09");
		put("\\uD83D\\uDE0A","\uD83D\uDE0A");
		put("\\uD83D\\uDE0B","\uD83D\uDE0B");
		put("\\uD83D\\uDE0C","\uD83D\uDE0C");
		put("\\uD83D\\uDE0D","\uD83D\uDE0D");
		put("\\uD83D\\uDE0E","\uD83D\uDE0E");
		put("\\uD83D\\uDE0F","\uD83D\uDE0F");
		
		put("\\uD83D\\uDE10","\uD83D\uDE10");
		put("\\uD83D\\uDE11","\uD83D\uDE11");
		put("\\uD83D\\uDE12","\uD83D\uDE12");
		put("\\uD83D\\uDE13","\uD83D\uDE13");
		put("\\uD83D\\uDE14","\uD83D\uDE14");
		put("\\uD83D\\uDE15","\uD83D\uDE15");
		put("\\uD83D\\uDE16","\uD83D\uDE16");
		put("\\uD83D\\uDE17","\uD83D\uDE17");
		put("\\uD83D\\uDE18","\uD83D\uDE18");
		put("\\uD83D\\uDE19","\uD83D\uDE19");
		put("\\uD83D\\uDE1A","\uD83D\uDE1A");
		put("\\uD83D\\uDE1B","\uD83D\uDE1B");
		put("\\uD83D\\uDE1C","\uD83D\uDE1C");
		put("\\uD83D\\uDE1D","\uD83D\uDE1D");
		put("\\uD83D\\uDE1E","\uD83D\uDE1E");
		put("\\uD83D\\uDE1F","\uD83D\uDE1F");
	}
	
	
	private void put(String key, String value) throws Exception
	{
		int len = key.length();
		if(len>LENMAX) throw new Exception("Unsupported key length: "+key.length());
		maps[len-1].put(key,value);
		fullMap.put(key,value);
	}
	
	
	public Object g() throws Exception
	{return fullMap;}


	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		for(int i=0;i<LENMAX;i++)
		s = replace(s, maps[LENMAX-1-i]);
		return s;
	}
	
	
	
	private String replace(String s, Map map)
	{
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