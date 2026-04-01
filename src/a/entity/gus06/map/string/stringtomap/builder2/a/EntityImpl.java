package a.entity.gus06.map.string.stringtomap.builder2.a;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141002";}
	
	public static final String DELIM = ";";
	
	public Object t(Object obj) throws Exception
	{
		Map map = new HashMap();
		String[] n = toArray(obj);
		if(n==null) return map;
		
		for(int i=0;i<n.length;i++)
		{
			String s = n[i];
			if(s.contains("="))
			{
				String[] k = s.split("=",2);
				map.put(k[0],k[1]);
			}
			else map.put(""+i,s);
		}
		return map;
	}
	
	private String[] toArray(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj.equals("")) return null;
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof String) return ((String) obj).split(DELIM);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
