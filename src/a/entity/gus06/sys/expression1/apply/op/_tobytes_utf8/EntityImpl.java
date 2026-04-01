package a.entity.gus06.sys.expression1.apply.op._tobytes_utf8;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170119";}

	public static final String CHARSET = "UTF-8";

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof byte[]) return new String((byte[]) obj).getBytes(CHARSET);
		if(obj instanceof String) return ((String) obj).getBytes(CHARSET);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}