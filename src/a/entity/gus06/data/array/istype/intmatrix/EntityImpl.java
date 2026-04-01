package a.entity.gus06.data.array.istype.intmatrix;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20170120";}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		for(Object o:oo) if(!(o instanceof int[][])) return false;
		return true;
	}
}