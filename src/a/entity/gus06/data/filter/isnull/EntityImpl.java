package a.entity.gus06.data.filter.isnull;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20141021";}
	
	public boolean f(Object obj) throws Exception
	{return obj==null;}
}
