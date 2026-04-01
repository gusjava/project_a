package a.entity.gus06.data.array.istype.intdoublearray;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20151205";}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		for(Object o:oo) if(!(o instanceof int[]) && !(o instanceof double[])) return false;
		return true;
	}
}