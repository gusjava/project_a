package a.entity.gus06.sys.expression1.apply.op._binary_inv;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160306";}
	
	private Service binaryToByte;
	
	public EntityImpl() throws Exception
	{
		binaryToByte = Outside.service(this,"gus06.convert.stringtobytearray.binary");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return binaryToByte.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
