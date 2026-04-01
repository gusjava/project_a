package a.entity.gus06.convert.objarraytogarray.strict;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170215";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		G[] yy = new G[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof G)) return null;
			yy[i] = (G) oo[i];
		}
		return yy;
	}
}