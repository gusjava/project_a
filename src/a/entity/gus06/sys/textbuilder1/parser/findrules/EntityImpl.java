package a.entity.gus06.sys.textbuilder1.parser.findrules;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160302";}

	public static final int LIMIT = 300;

	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Map map1 = new HashMap();
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(!key.startsWith("format."))
			{
				String value = (String) map.get(key);
				String value1 = resolveValue(0,map,value);
				map1.put(key,value1);
			}
		}
		return map1;
	}
	
	
	
	private String resolveValue(int l, Map map, String text) throws Exception
	{
		if(l>LIMIT) throw new Exception("Limit exceeded: "+l);
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(text.contains("["+key+"]"))
			{
				String value = (String) map.get(key);
				String value1 = resolveValue(l+1,map,value);
				text = text.replace("["+key+"]",value1);
			}
		}
		return text;
	}
}
