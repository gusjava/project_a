package a.entity.gus06.command.appfile.replace.handle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20140707";}
	
	
	public void v(String key, Object obj) throws Exception
	{
		Map map = (Map) obj;
		Map map1 = new HashMap();
		
		String[] n = key.split("=",2);
		if(n.length!=2) throw new Exception("Invalid rule: "+key);
		
		String s1 = n[0];
		String s2 = n[1];
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String k = (String) it.next();
			String v = (String) map.get(k);
			
			String k1 = k.replace(s1,s2);
			String v1 = v.replace(s1,s2);
			
			map1.put(k1,v1);
		}
		
		map.clear();
		map.putAll(map1);
	}
}
