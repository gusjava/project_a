package a.entity.gus06.convert.objarraytolongarray.longint;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170102";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		long[] yy = new long[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof Long) && !(oo[i] instanceof Integer)) return null;
			yy[i] = ((Number) oo[i]).longValue();
		}
		return yy;
	}
}