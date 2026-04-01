package a.entity.gus06.sys.webserver1.format.output.header.map;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141107";}
	
	public static final String SERVERNAME = "GusWebServer_v1";

	public static final String KEY_TYPE = "type";
	public static final String KEY_VERSION = "version";
	public static final String KEY_CONTENT = "content";
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String type = (String) get(map,KEY_TYPE);
		String version = (String) get(map,KEY_VERSION);
		byte[] content = (byte[]) get(map,KEY_CONTENT);
		
		Map h = new HashMap();
		h.put("Server",SERVERNAME);
		h.put("Connection",connectionType(version));
		h.put("Content-Type",contentType(type));
		h.put("Content-Length",""+content.length);
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(key.startsWith("header."))
			{
				String value = (String) map.get(key);
				h.put(key.substring(7),value);
			}
		}
		
		return h;
	}
	
	
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
	
	
	private String contentType(String type) throws Exception
	{
		if(type.equals("bmp")) return "image/bmp";
		if(type.equals("jpg")) return "image/jpeg";
		if(type.equals("jpeg")) return "image/jpeg";
		if(type.equals("gif")) return "image/gif";
		if(type.equals("png")) return "image/png";
		if(type.equals("ico")) return "image/ico";
		
		if(type.equals("htm")) return "text/html";
		if(type.equals("htm")) return "text/html";
		if(type.equals("php")) return "text/html";
		if(type.equals("jsp")) return "text/html";
		
		if(type.equals("xml")) return "text/xml";
		if(type.equals("txt")) return "text/txt";
		if(type.equals("css")) return "text/css";
		if(type.equals("js")) return "text/js";
		
		if(type.equals("zip")) return "application/x-zip-compressed";
		if(type.equals("jar")) return "application/x-zip-compressed";
		if(type.equals("exe")) return "application/octet-stream";
		
		return "text/html";
	}
	
	
	
	
	private String connectionType(String version)
	{
		if(version.equals("HTTP/1.0")) return "close";
		return "keep-alive";
	}
}
