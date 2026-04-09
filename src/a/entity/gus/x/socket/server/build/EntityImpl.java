package a.entity.gus.x.socket.server.build;

import a.framework.*;
import java.net.InetSocketAddress;
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

		ServerSocket ss = bind(port);
		cache.put(key, ss);
		return serverAt(key);
	}
	
	private static final int BIND_RETRIES  = 10;
	private static final int BIND_DELAY_MS = 500;

	private ServerSocket bind(int port) throws Exception
	{
		for(int i=0; i<BIND_RETRIES; i++)
		{
			try
			{
				ServerSocket ss = new ServerSocket();
				ss.setReuseAddress(true);
				ss.bind(new InetSocketAddress(port));
				return ss;
			}
			catch(java.net.BindException e)
			{
				if(i+1==BIND_RETRIES) throw e;
				Thread.sleep(BIND_DELAY_MS);
			}
		}
		throw new Exception("bind failed after "+BIND_RETRIES+" attempts");
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