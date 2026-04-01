package a.entity.gus06.command.sleep;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140721";}
	
	
	public void p(Object obj) throws Exception
	{
		Thread.sleep(toLong(obj));
	}
	
	
	
	private long toLong(Object obj) throws Exception
	{
		if(obj instanceof Long) return ((Long) obj).longValue();
		if(obj instanceof String) return Long.parseLong((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
