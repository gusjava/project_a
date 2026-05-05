package a.entity.gus06.sys.expression1.apply.op._md5hexa;

import a.framework.*;
import java.io.File;
import java.net.URL;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231122";}


	private Service md5;
	
	public EntityImpl() throws Exception
	{
		md5 = Outside.service(this,"gus.y.crypto1.hash.md5.hexa");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof byte[]) return md5.t(obj);
		if(obj instanceof String) return md5.t(obj);
		if(obj instanceof InputStream) return md5.t(obj);
		if(obj instanceof File) return md5.t(obj);
		if(obj instanceof URL) return md5.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}