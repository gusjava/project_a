package a.entity.gus06.sys.expression1.apply.op._address;

import a.framework.*;
import java.net.Socket;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180321";}

	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof Socket) return ((Socket) obj).getInetAddress().getHostAddress();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
