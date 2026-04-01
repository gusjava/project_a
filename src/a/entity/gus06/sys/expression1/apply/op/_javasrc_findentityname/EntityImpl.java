package a.entity.gus06.sys.expression1.apply.op._javasrc_findentityname;

import a.framework.*;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251204";}

	private Service valid;
	
	public EntityImpl() throws Exception
	{
		valid = Outside.service(this,"gus06.java.srccode.entity.isvalid");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return valid.t(obj);
		if(obj instanceof File) return valid.t(obj);
		if(obj instanceof InputStream) return valid.t(obj);
		if(obj instanceof Reader) return valid.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
