package a.entity.gus06.math.tablong.product;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151203";}

	
	public Object t(Object obj) throws Exception
	{
		long[] nn = (long[]) obj;
		long product = 1;
		for(long n:nn) product *= n;
		return Long.valueOf(product);
	}
}
