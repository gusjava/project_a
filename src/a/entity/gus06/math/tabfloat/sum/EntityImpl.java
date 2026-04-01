package a.entity.gus06.math.tabfloat.sum;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151203";}

	
	public Object t(Object obj) throws Exception
	{
		float[] dd = (float[]) obj;
		float sum = 0;
		for(float d:dd) sum += d;
		return Float.valueOf(sum);
	}
}
