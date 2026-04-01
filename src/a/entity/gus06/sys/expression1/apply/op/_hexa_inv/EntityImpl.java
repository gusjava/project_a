package a.entity.gus06.sys.expression1.apply.op._hexa_inv;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160305";}
	
	private Service hexaToByte;
	
	public EntityImpl() throws Exception
	{
		hexaToByte = Outside.service(this,"gus06.convert.stringtobytearray.hexa");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return hexaToByte.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
