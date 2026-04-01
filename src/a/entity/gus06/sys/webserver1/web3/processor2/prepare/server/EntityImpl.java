package a.entity.gus06.sys.webserver1.web3.processor2.prepare.server;

import a.framework.*;
import java.nio.channels.ServerSocketChannel;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160220";}

	public static final String KEY_HOST = "host";
	public static final String KEY_PORT = "port";

	private Service engine;
	
	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.webserver1.main.engine");
	}
	
	
	public Object g() throws Exception
	{
		ServerSocketChannel ch = (ServerSocketChannel) engine.r("serverChannel");
		
		Map map = new HashMap();
		map.put(KEY_HOST,address(ch));
		map.put(KEY_PORT,port(ch));
		
		return map;
	}
	
	
	private String address(ServerSocketChannel ch)
	{return ch.socket().getInetAddress().getHostName();}
	
	
	private String port(ServerSocketChannel ch)
	{return ""+ch.socket().getLocalPort();}
}
