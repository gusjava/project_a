package a.entity.gus06.web.allocine.convert.query1todata1.format;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200113";}
	


	
	public Object t(Object obj) throws Exception
	{
		Map inputMap = (Map) obj;
		
		Map map = new HashMap();
		addToMap(map,inputMap);
		return map;
	}
	
	
	
	private void addToMap(Map map, Map inputMap)
	{
		Iterator it = inputMap.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Object value = inputMap.get(key);
			
			if(key.equals("castingShort"))
			{
				addToMap(map,(Map) value);
			}
			else if(key.equals("release"))
			{
				addToMap(map,(Map) value);
			}
			else if(key.equals("statistics"))
			{
				addToMap(map,(Map) value);
			}
			
			
			else if(key.equals("genre"))
			{
				map.put("genre",extractList1((List) value));
			}
			else if(key.equals("nationality"))
			{
				map.put("nationality",extractList1((List) value));
			}
			
			else if(key.equals("movieType"))
			{
				map.put("movietype",extractDollar((Map) value));
			}
			else if(key.equals("trailer"))
			{
				map.put("trailer",extractHref((Map) value));
			}
			else if(key.equals("poster"))
			{
				map.put("poster",extractHref((Map) value));
			}
			
			else if(key.equals("movieCertificate"))
			{
				Map m = (Map) pick((Map) value);
				map.put("moviecertificate",extractDollar(m));
			}
			else if(key.equals("link"))
			{
				Map m = (Map) pick((List) value);
				map.put("link",extractHref(m));
			}
			else map.put(key.toLowerCase(),value);
		}
	}
	
	
	
	
	private String extractList1(List l)
	{
		StringBuffer b = new StringBuffer();
		int nb = l.size();
		for(int i=0;i<nb;i++)
		{
			Map m = (Map) l.get(i);
			b.append(extractDollar(m));
			if(i<nb-1) b.append(";");
		}
		return b.toString();
	}
	
	private String extractDollar(Map m)
	{
		return (String) m.get("$");
	}
	
	private String extractHref(Map m)
	{
		return (String) m.get("href");
	}
	
	private Object pick(Map m)
	{
		return m.values().iterator().next();
	}
	
	private Object pick(List l)
	{
		return l.get(0);
	}
}
