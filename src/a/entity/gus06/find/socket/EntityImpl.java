package a.entity.gus06.find.socket;

import a.framework.*;
import java.net.Socket;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180321";}


	private Service builder;

	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.socket.builder1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof Socket) return obj;
		if(obj instanceof String) return builder.t(obj);
		if(obj instanceof Map) return builder.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
