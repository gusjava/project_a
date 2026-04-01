package a.entity.gus06.find.stacktrace;

import a.framework.*;
import javax.swing.border.Border;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180106";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof StackTraceElement[])  return obj;
		if(obj instanceof Exception) return ((Exception) obj).getStackTrace();
		if(obj instanceof Thread) return ((Thread) obj).getStackTrace();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
