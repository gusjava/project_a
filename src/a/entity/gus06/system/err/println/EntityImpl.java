package a.entity.gus06.system.err.println;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140730";}
	
	
	public void p(Object obj) throws Exception
	{System.err.println(obj);}
}
