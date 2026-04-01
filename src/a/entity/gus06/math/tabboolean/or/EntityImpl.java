package a.entity.gus06.math.tabboolean.or;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151030";}

	
	public Object t(Object obj) throws Exception
	{
		boolean[] tt = (boolean[]) obj;
		for(boolean t:tt) if(t) return Boolean.TRUE;
		return Boolean.FALSE;
	}
}
