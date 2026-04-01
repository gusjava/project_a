package a.entity.gus06.convert.objarraytolongarray.strict;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151203";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		long[] yy = new long[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof Long)) return null;
			yy[i] = ((Long) oo[i]).longValue();
		}
		return yy;
	}
}