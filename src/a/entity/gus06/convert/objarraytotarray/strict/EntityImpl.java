package a.entity.gus06.convert.objarraytotarray.strict;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151030";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		T[] yy = new T[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof T)) return null;
			yy[i] = (T) oo[i];
		}
		return yy;
	}
}