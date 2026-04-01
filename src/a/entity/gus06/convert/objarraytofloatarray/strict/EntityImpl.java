package a.entity.gus06.convert.objarraytofloatarray.strict;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151203";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		float[] yy = new float[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof Float)) return null;
			yy[i] = ((Float) oo[i]).floatValue();
		}
		return yy;
	}
}