package a.entity.gus06.sys.webserver1.web3.processor2.prepare.client;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160220";}
	
	
	public static final String KEY_ADDRESS = "address";
	public static final String KEY_PORT = "port";
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map input = (Map) obj;
		
		String address = (String) get(input,KEY_ADDRESS);
		String port = (String) get(input,KEY_PORT);
		
		Map map = new HashMap();
		map.put(KEY_ADDRESS,address);
		map.put(KEY_PORT,port);
		
		return map;
	}
	
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
}
