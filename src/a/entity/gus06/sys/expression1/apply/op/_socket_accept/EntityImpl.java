package a.entity.gus06.sys.expression1.apply.op._socket_accept;

import a.framework.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180312";}



	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof ServerSocket) return accept((ServerSocket) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Socket accept(ServerSocket server) throws Exception
	{
		return server.accept();
	}
}
