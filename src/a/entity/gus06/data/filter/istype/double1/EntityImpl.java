package a.entity.gus06.data.filter.istype.double1;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160818";}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		return obj instanceof Double;
	}
	
}
