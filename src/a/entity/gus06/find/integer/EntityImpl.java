package a.entity.gus06.find.integer;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20150529";}
	
	private Service stringToInteger;
	private Service booleanToInteger;

	public EntityImpl() throws Exception
	{
		stringToInteger = Outside.service(this,"gus06.convert.stringtointeger");
		booleanToInteger = Outside.service(this,"gus06.convert.booleantointeger");
	}

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Integer) return obj;
		if(obj instanceof String) return stringToInteger.t(obj);
		if(obj instanceof Boolean) return booleanToInteger.t(obj);
		
		if(obj instanceof Long) return Integer.valueOf(((Long)obj).intValue());
		if(obj instanceof Double) return Integer.valueOf(((Double)obj).intValue());
		if(obj instanceof Float) return Integer.valueOf(((Float)obj).intValue());
		if(obj instanceof Short) return Integer.valueOf(((Short)obj).intValue());
		if(obj instanceof Byte) return Integer.valueOf(((Byte)obj).intValue());
		
		throw new Exception("Invalid type: "+obj.getClass().getName());
	}
}
