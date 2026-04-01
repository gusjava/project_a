package a.entity.gus06.sys.expression1.apply.op._sha384;

import a.framework.*;
import java.io.File;
import java.net.URL;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160304";}


	private Service build;
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.crypto.hash.sha384");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof byte[]) return build.t(obj);
		if(obj instanceof String) return build.t(obj);
		if(obj instanceof InputStream) return build.t(obj);
		if(obj instanceof File) return build.t(obj);
		if(obj instanceof URL) return build.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
