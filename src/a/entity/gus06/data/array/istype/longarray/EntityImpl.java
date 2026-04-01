package a.entity.gus06.data.array.istype.longarray;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160705";}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		for(Object o:oo) if(!(o instanceof long[])) return false;
		return true;
	}
}