package a.entity.gus06.find.bytearray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140929";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof byte[]) return obj;
		if(obj instanceof String) return ((String) obj).getBytes("UTF8");
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
