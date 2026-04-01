package a.entity.gus06.exception.generate;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140730";}


	
	
	public void p(Object obj) throws Exception
	{
		Exception e = toEx(obj);
		Outside.err(this,"p(Object)",e);
	}
	
	private Exception toEx(Object obj) throws Exception
	{
		if(obj instanceof Exception) return (Exception) obj;
		if(obj instanceof String) return new Exception((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
