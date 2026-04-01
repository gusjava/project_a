package a.entity.gus06.data.filter.isnnull;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20231107";}
	
	public boolean f(Object obj) throws Exception
	{return obj!=null;}
}