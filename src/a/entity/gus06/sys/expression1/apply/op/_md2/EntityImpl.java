package a.entity.gus06.sys.expression1.apply.op._md2;

import a.framework.*;
import java.io.File;
import java.net.URL;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180203";}


	private Service md2;
	
	public EntityImpl() throws Exception
	{
		md2 = Outside.service(this,"gus06.crypto.hash.md2");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof byte[]) return md2.t(obj);
		if(obj instanceof String) return md2.t(obj);
		if(obj instanceof InputStream) return md2.t(obj);
		if(obj instanceof File) return md2.t(obj);
		if(obj instanceof URL) return md2.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
