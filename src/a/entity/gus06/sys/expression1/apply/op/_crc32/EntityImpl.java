package a.entity.gus06.sys.expression1.apply.op._crc32;

import a.framework.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160307";}


	private Service crc;
	
	public EntityImpl() throws Exception
	{crc = Outside.service(this,"gus06.crypto.checksum.crc32.v2");}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof byte[]) return crc.t(obj);
		if(obj instanceof String) return crc.t(obj);
		if(obj instanceof InputStream) return crc.t(obj);
		if(obj instanceof File) return crc.t(obj);
		if(obj instanceof URL) return crc.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
