package a.entity.gus06.system.out.println;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140727";}
	
	
	public void p(Object obj) throws Exception
	{System.out.println(obj);}
}