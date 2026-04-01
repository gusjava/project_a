package a.entity.gus06.math.tabint.product;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151030";}

	
	public Object t(Object obj) throws Exception
	{
		int[] nn = (int[]) obj;
		int product = 1;
		for(int n:nn) product *= n;
		return Integer.valueOf(product);
	}
}
