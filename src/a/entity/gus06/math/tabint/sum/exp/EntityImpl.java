package a.entity.gus06.math.tabint.sum.exp;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20171015";}

	
	public Object t(Object obj) throws Exception
	{
		int[] nn = (int[]) obj;
		double sum = 0;
		for(int n:nn) sum += Math.exp(n);
		return Double.valueOf(sum);
	}
}
