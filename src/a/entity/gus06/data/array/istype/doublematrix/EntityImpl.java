package a.entity.gus06.data.array.istype.doublematrix;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20170120";}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		for(Object o:oo) if(!(o instanceof double[][])) return false;
		return true;
	}
}