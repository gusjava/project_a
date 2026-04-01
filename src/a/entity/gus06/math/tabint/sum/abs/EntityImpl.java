package a.entity.gus06.math.tabint.sum.abs;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151209";}

	
	public Object t(Object obj) throws Exception
	{
		int[] nn = (int[]) obj;
		int sum = 0;
		for(int n:nn) sum += Math.abs(n);
		return Integer.valueOf(sum);
	}
}
