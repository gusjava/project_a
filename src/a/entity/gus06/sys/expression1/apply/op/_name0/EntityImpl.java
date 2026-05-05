package a.entity.gus06.sys.expression1.apply.op._name0;

import a.framework.*;
import java.io.File;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}


	private Service performFile;
	private Service performURL;
	
	public EntityImpl() throws Exception
	{
		performFile = Outside.service(this,"gus.x.file.getname0");
		performURL = Outside.service(this,"gus06.url.getname0");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return performFile.t(obj);
		if(obj instanceof File) return performFile.t(obj);
		if(obj instanceof URL) return performURL.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
