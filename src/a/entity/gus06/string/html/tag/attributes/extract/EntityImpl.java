package a.entity.gus06.string.html.tag.attributes.extract;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190702";}

	public static final String REGEX1 = "[a-zA-Z]+ *= *\"[^\"]+\"";
	public static final String REGEX2 = "[a-zA-Z]+ *= *'[^']+'";

	
	private Service extract;

	private Pattern p1;
	private Pattern p2;
	

	public EntityImpl() throws Exception
	{
		extract = Outside.service(this,"gus06.string.extract.match.all");
		
		p1 = Pattern.compile(REGEX1,Pattern.DOTALL);
		p2 = Pattern.compile(REGEX2,Pattern.DOTALL);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = ((String) obj).trim();
		Map map = new HashMap();
		
		List l1 = (List) extract.t(new Object[]{s,p1});
		List l2 = (List) extract.t(new Object[]{s,p2});
		
		for(int i=0;i<l1.size();i++)
		{
			String el = (String) l1.get(i);
			handle(map,el);
		}
		for(int i=0;i<l2.size();i++)
		{
			String el = (String) l2.get(i);
			handle(map,el);
		}
		
		return map;
	}
	
	
	private void handle(Map map, String el)
	{
		String[] nn = el.split("=",2);
		String key = nn[0].trim();
		String value = nn[1].trim();
		value = value.substring(1,value.length()-1);
		
		map.put(key,value);
	}
}
