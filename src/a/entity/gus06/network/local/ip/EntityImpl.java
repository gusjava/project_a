package a.entity.gus06.network.local.ip;

import java.net.InetAddress;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140706";}
	
	
	public Object g() throws Exception
	{
		return InetAddress.getLocalHost().getHostAddress();
	}
}
