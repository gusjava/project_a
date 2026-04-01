package a.entity.gus06.sys.filemapper1.mapping;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160212";}
	
	public static final String S = File.separator;
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = format((Map) o[0]);
		String id = format((String) o[1]);
		String key = format((String) o[2]);
		
		if(key==null) throw new Exception("Invalid null key for file mapping");
		
		if(map==null) return key;
		if(id==null) return key;
		
		if(map.containsKey(id+"@"+key))
		return map.get(id+"@"+key);
		
		if(!id.startsWith(S))
		{
			while(id.length()>0)
			{
				id = shorten(id);
				if(map.containsKey(id+"@"+key))
				return map.get(id+"@"+key);
			}
		}
		
		if(map.containsKey(key))
			return map.get(key);
		return key;
	}
	
	
	
	private String shorten(String id)
	{
		int i = id.lastIndexOf(".");
		if(i==-1) return "";
		return id.substring(0,i);
	}
	
	
	
	
	
	
	private Map format(Map map)
	{
		if(map==null) return null;
		Map map1 = new HashMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) map.get(key);
			map1.put(format(key),format(value));
		}
		return map1;
	}
	
	private String format(String s)
	{
		if(s==null) return null;
		return s.replace("\\",S).replace("/",S);
	}
}
