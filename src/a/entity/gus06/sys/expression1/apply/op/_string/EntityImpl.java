package a.entity.gus06.sys.expression1.apply.op._string;

import a.framework.*;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}


	private Service handleInputStream;
	
	public EntityImpl() throws Exception
	{
		handleInputStream = Outside.service(this,"gus06.io.transfer.tostring.autodetect");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof byte[]) return new String((byte[]) obj);
		if(obj instanceof InputStream) return handleInputStream.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}