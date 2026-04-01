package a.entity.gus06.tostring.stacktraceelement;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141122";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof StackTraceElement)
			return toString((StackTraceElement)obj);
		throw new Exception("Invalid data type: "+obj);
	}
	
	
	private String toString(StackTraceElement ste)
	{
		if(ste==null) return "null";
		return ste.getClassName()+"@"+ste.getMethodName()+" ("+ste.getFileName()+":"+ste.getLineNumber()+")";
	}
}
