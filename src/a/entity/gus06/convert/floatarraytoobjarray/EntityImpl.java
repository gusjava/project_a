package a.entity.gus06.convert.floatarraytoobjarray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160626";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		float[] oo = (float[]) obj;
		int number = oo.length;
		
		Float[] yy = new Float[number];
		for(int i=0;i<number;i++) yy[i] = Float.valueOf(oo[i]);
		return yy;
	}
}
