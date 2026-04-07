package a.entity.gus.x.socket.server.build;

import a.framework.*;
import java.net.ServerSocket;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl extends S1 implements Entity, T, G {
	public String creationDate() {return "20180312";}
	
	private Map cache;
	//FIXME : rendre threadsafe
	
	public EntityImpl() throws Exception
	{cache = new HashMap();}
	
	public Object g() throws Exception
	{return cache;} //DOTO faire une copie ?

	public Object t(Object obj) throws Exception
	{
		int port = toInt(obj);
		String key = ""+port;
		
		resetKey(key);
		
		cache.put(key, new ServerSocket(port));
		return serverAt(key);
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	private ServerSocket serverAt(String key)
	{return (ServerSocket) cache.get(key);}
	
	private void resetKey(String key) throws Exception
	{
		if(!cache.containsKey(key)) return;
		serverAt(key).close();
		cache.remove(key);
	}
}