package a.entity.gus06.file.properties.perform.apply.script1.build.rules;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150925";}
	
	
	public Object t(Object obj) throws Exception
	{return buildRules((String) obj);}

	
	private Map buildRules(String script) throws Exception
	{
		Map rules = new HashMap();
		
		String[] lines = script.split("\n");
		for(String line:lines) if(!line.trim().equals("") && !line.startsWith("#"))
		{
			if(!line.contains("=")) throw new Exception("Invalid script line: "+line);
			String[] n = line.split("=",2);
			rules.put(n[0],n[1]);
		}
		return rules;
	}
}
