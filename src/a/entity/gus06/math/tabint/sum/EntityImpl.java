package a.entity.gus06.math.tabint.sum;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150708";}
	
	public static final int MAX = Integer.MAX_VALUE;

	
	public Object t(Object obj) throws Exception
	{
		int[] nn = (int[]) obj;
		long sum = 0;
		for(int n:nn) sum += n;
		
		if(sum<=MAX && sum>=-MAX) return Integer.valueOf((int) sum);
		return Long.valueOf(sum);
	}
}
