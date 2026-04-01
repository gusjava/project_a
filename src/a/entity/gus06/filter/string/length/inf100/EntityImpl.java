package a.entity.gus06.filter.string.length.inf100;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160817";}
	
	public static final int LIMIT = 100;
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.length()<=LIMIT;
	}
}