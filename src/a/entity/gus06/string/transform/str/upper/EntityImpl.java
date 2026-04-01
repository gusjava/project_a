package a.entity.gus06.string.transform.str.upper;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141002";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.toUpperCase();
	}
}
