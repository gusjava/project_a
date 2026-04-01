package a.entity.gus06.mysql.socketfactory.gus05.init;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Properties;
import a.framework.*;

//import com.mysql.cj.protocol.SocketFactory;
//import com.mysql.cj.protocol.StandardSocketFactory;
//import com.mysql.cj.protocol.GusSocketFactoryHolder;

public class EntityImpl implements Entity {

	public String creationDate() {return "20170206";}


	private Service socketWrapper;

	public EntityImpl() throws Exception
	{
		socketWrapper = Outside.service(this,"gus06.mysql.socketfactory.wrapper");
//		GusSocketFactoryHolder.socketFactory = new SocketFactory1();
	}
	
	private Socket wrapSocket(Socket socket)
	{
		try{return (Socket) socketWrapper.t(socket);}
		catch(Exception e){Outside.err(this,"wrapSocket(Socket)",e);}
		return socket;
	}
	
	
//	public class SocketFactory1 extends StandardSocketFactory
//	{
//		protected Socket createSocket(Properties props)
//		{
//			Socket socket = super.createSocket(props);
//			return wrapSocket(socket);
//		}
//	}
}