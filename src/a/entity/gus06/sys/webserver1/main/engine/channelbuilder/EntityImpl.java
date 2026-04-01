package a.entity.gus06.sys.webserver1.main.engine.channelbuilder;

import a.framework.*;
import java.nio.channels.*;
import java.nio.ByteBuffer;
import java.net.InetSocketAddress;
import java.util.Map;


public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160220";}

	public static final int DEFAULT_PORT = 80;
	public static final String KEY_PORT = "app.webserver.port";

	private Map prop;
	private int port;
	
	
	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"prop");
		port = prop.containsKey(KEY_PORT)?intProp(KEY_PORT):DEFAULT_PORT;
	}
	
	public Object g() throws Exception
	{
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.socket().bind(new InetSocketAddress(port));
		serverChannel.configureBlocking(false);
		return serverChannel;
	}

	
	private int intProp(String key)
	{return Integer.parseInt((String) prop.get(key));}
}
