package a.entity.gus06.sys.expression1.apply.op._is_closed;

import a.framework.*;
import java.net.ServerSocket;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180312";}


	private Service findWindow;
	
	public EntityImpl() throws Exception
	{
		findWindow = Outside.service(this,"gus06.find.window");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		
		if(obj instanceof ServerSocket) return ((ServerSocket) obj).isClosed();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
