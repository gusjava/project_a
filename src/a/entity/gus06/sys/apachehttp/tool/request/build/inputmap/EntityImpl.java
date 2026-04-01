package a.entity.gus06.sys.apachehttp.tool.request.build.inputmap;

import a.framework.*;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190706";}

	public static final String KEY_URL = "url";

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Map)		return (Map) obj;
		if(obj instanceof String)	return stringToMap((String) obj);
		if(obj instanceof URL)		return urlToMap((URL) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Map stringToMap(String url)
	{
		Map map = new HashMap();
		map.put(KEY_URL,url);
		return map;
	}
	
	private Map urlToMap(URL url)
	{
		Map map = new HashMap();
		map.put(KEY_URL,url);
		return map;
	}
}
