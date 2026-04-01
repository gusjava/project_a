package a.entity.gus06.convert.objarraytostringarray.strict;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151201";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		String[] yy = new String[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof String)) return null;
			yy[i] = (String) oo[i];
		}
		return yy;
	}
}
