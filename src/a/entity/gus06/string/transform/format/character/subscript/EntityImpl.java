package a.entity.gus06.string.transform.format.character.subscript;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20231104";}
	
	public final static int LENMAX = 2;
	
	private Map[] maps;
	private Map fullMap;
	
	public EntityImpl() throws Exception
	{
		maps = new Map[LENMAX];
		for(int i=0;i<LENMAX;i++) maps[i] = new HashMap();
		fullMap = new HashMap();
		
		put("1","\u2081");
		put("2","\u2082");
		put("3","\u2083");
		put("4","\u2084");
		put("5","\u2085");
		put("6","\u2086");
		put("7","\u2087");
		put("8","\u2088");
		put("9","\u2089");
		put("+","\u208a");
		put("-","\u208b");
		put("=","\u208c");
		put("(","\u208d");
		put(")","\u208e");
		put("a","\u2090");
		put("e","\u2091");
		put("o","\u2092");
		put("x","\u2093");
		put("h","\u2095");
		put("k","\u2096");
		put("l","\u2097");
		put("m","\u2098");
		put("n","\u2099");
		put("p","\u209a");
		put("s","\u209b");
		put("t","\u209c");
		put("10","\u23e8");
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