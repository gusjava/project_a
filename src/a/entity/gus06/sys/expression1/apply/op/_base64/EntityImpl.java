package a.entity.gus06.sys.expression1.apply.op._base64;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	private Service byteToBase64;
	
	public EntityImpl() throws Exception
	{
		byteToBase64 = Outside.service(this,"gus06.tostring.bytetobase64");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof byte[]) return byteToBase64.t(obj);
		if(obj instanceof String) return byteToBase64.t(((String) obj).getBytes("UTF8"));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}
