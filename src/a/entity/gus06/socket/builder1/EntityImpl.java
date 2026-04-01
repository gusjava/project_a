package a.entity.gus06.socket.builder1;

import a.framework.*;
import java.util.Map;
import java.net.InetAddress;
import java.net.Socket;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180319";}
	
	public static final String KEY_IP = "ip";
	public static final String KEY_PORT = "port";
	


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String) return fromString((String) obj);
		if(obj instanceof Map) return fromMap((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Socket fromString(String s) throws Exception
	{
		String[] n = s.split(":");
			
		String ip = n[0];
		int port = toInt(n[1]);
		
		InetAddress inetAddress = InetAddress.getByName(ip);
           	return new Socket(inetAddress,port);
	}
	
	private Socket fromMap(Map m) throws Exception
	{
		String ip = (String) get(m,KEY_IP);
		int port = toInt(get(m,KEY_PORT));
		
		InetAddress inetAddress = InetAddress.getByName(ip);
           	return new Socket(inetAddress,port);
	}
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}
