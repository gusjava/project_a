package a.entity.gus06.sys.expression1.apply.op._crc32_flyway;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190328";}


	private Service crc;
	
	public EntityImpl() throws Exception
	{crc = Outside.service(this,"gus06.crypto.checksum.crc32.flyway");}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return crc.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
