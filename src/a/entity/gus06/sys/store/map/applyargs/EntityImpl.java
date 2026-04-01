package a.entity.gus06.sys.store.map.applyargs;

import a.framework.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20140907";}

	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key==null || key.equals("")) return;
		String[] args = key.split("\\|");
    	
		Map map = (Map) obj;
		Map map1 = applyArgs(args,map);
        
		map.clear();
		map.putAll(map1);
	}
	
	
	private Map applyArgs(String[] args, Map map)
	{
		Map newMap = new HashMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) map.get(key);
    		
			String key1 = applyArgs(args,key);
			String value1 = applyArgs(args,value);
    		
			newMap.put(key1,value1);
		}
		return newMap;
	}
	
	
	
	
	private String applyArgs(String[] args, String s)
	{
		for(int i=0;i<args.length;i++)
		s = s.replace("<"+i+">",args[i]);
		return s;
	}
}
