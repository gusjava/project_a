package a.entity.gus06.filter.string.is.alphanum;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160403";}
	
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.matches("[a-zA-Z0-9]+");
	}
}