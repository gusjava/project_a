package a.entity.gus06.find.byte1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180331";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Byte) return obj;
		
		if(obj instanceof Integer)
		{
			int v = ((Integer) obj).intValue();
			return Byte.valueOf((byte) v);
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
