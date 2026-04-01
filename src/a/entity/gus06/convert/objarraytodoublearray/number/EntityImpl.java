package a.entity.gus06.convert.objarraytodoublearray.number;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170102";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		double[] yy = new double[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof Number)) return null;
			yy[i] = ((Number) oo[i]).doubleValue();
		}
		return yy;
	}
}