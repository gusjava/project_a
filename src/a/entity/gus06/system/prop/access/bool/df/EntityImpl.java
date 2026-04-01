package a.entity.gus06.system.prop.access.bool.df;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20161015";}
	
	
	
	public boolean f(Object obj) throws Exception
	{return Boolean.getBoolean((String) obj);}
}