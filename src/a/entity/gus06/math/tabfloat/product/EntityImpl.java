package a.entity.gus06.math.tabfloat.product;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151203";}

	
	public Object t(Object obj) throws Exception
	{
		float[] dd = (float[]) obj;
		float product = 1;
		for(float d:dd) product *= d;
		return Float.valueOf(product);
	}
}
