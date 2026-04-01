package a.entity.gus06.sys.expression1.apply.op._base64_inv;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160305";}
	
	private Service base64ToByte;
	
	public EntityImpl() throws Exception
	{
		base64ToByte = Outside.service(this,"gus06.convert.stringtobytearray.base64");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return base64ToByte.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
