package a.entity.gus06.java.srccode.extract.entity.constants.string;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140827";}

	private Pattern p = Pattern.compile("([a-zA-Z0-9]+) ?= ?\"([^\"]+)\"");
	
	
	private Service toArray;

	public EntityImpl() throws Exception
	{toArray = Outside.service(this,"gus06.java.srccode.toarray");}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		Map map = new HashMap();
		
		String[] lines = (String[]) toArray.t(obj);
		for(String line:lines)
		{
			if(line.startsWith("public static final String "))
			{
				Matcher m = p.matcher(line);
				if(!m.find()) throw new Exception("Constant not found inside line: "+line);
				
				String key = m.group(1);
				String value = m.group(2);
				map.put(key,value);
			}
		}
		return map;
	}
}
