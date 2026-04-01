package a.entity.gus06.find.exception;

import a.framework.*;
import javax.swing.border.Border;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160414";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Exception) return obj;
		if(obj instanceof String) return new Exception((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
