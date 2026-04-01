package a.entity.gus06.command.socket.socketbuilder;

import java.net.Socket;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140704";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String info = (String) obj;
		String connect = findConnect(info);
		String[] n = connect.split(":");
		if(n.length!=2) throw new Exception("Invalid connect info: "+info); 
		
		String host = n[0];
		int port = Integer.parseInt(n[1]);
		return new Socket(host,port);
	}
	
	
	
	private String findConnect(String info) throws Exception
	{
		if(info!=null) return info;
		
		info = (String) Outside.resource(this,"property#socket.connect");
		if(info!=null) return info;
		
		throw new Exception("Connect info could not be found");
	}
}
