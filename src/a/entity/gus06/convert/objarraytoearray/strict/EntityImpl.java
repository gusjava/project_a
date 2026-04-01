package a.entity.gus06.convert.objarraytoearray.strict;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151030";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		E[] yy = new E[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof E)) return null;
			yy[i] = (E) oo[i];
		}
		return yy;
	}
}