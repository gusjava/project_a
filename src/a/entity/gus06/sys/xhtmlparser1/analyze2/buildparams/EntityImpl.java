package a.entity.gus06.sys.xhtmlparser1.analyze2.buildparams;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170226";}
	
	public static final Pattern P1 = Pattern.compile("([^= \\t\\n]+)[ \\t\\n]*=[ \\t\\n]*\"([^\"]*)\"",Pattern.DOTALL);
	public static final Pattern P2 = Pattern.compile("([^= \\t\\n]+)[ \\t\\n]*=[ \\t\\n]*'([^']*)'",Pattern.DOTALL);
	public static final Pattern P3 = Pattern.compile("([^= \\t\\n]+)[ \\t\\n]*=[ \\t\\n]*'([^']*)'",Pattern.DOTALL);

	// INSUFFISANT POUR PARSER LES PARAMS
	// IL FAUT DEFINIR UN VRAI PARSEUR
	
	
	public Object t(Object obj) throws Exception
	{
		String params = (String) obj;
		Map map = new HashMap();
		if(params==null) return map;
		
		int nb = params.length();
		for(int i=0;i<nb;i++)
		{
			char c = params.charAt(i);
			// ...
		}
		
		Matcher m1 = P1.matcher(params);
		while(m1.find())
		{
			String key = m1.group(1);
			String value = m1.group(2);
			
			if(map.containsKey(key))
			{
				String value0 = (String) map.get(key);
				map.put(key,value0.trim()+" "+value.trim());
			}
			else map.put(key,value);
		}
		
		Matcher m2 = P2.matcher(params);
		while(m2.find())
		{
			String key = m2.group(1);
			String value = m2.group(2);
			
			if(map.containsKey(key))
			{
				String value0 = (String) map.get(key);
				map.put(key,value0.trim()+" "+value.trim());
			}
			else map.put(key,value);
		}
		return map;
	}
}