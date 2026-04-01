package a.entity.gus06.command.transfer.socketbuilder;

import java.net.Socket;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140705";}

	
	
	public Object g() throws Exception
	{
		String info = (String) Outside.resource(this,"property#transfer.connect");
		if(info==null) throw new Exception("Undefined property: transfer.connect");
		
		String[] n = info.split(":");
		if(n.length!=2) throw new Exception("Invalid connect info: "+info); 
		
		String host = n[0];
		int port = Integer.parseInt(n[1]);
		
		return new Socket(host,port);
	}
}
