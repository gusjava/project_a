package a.entity.gus06.math.tabint.normalize.softmax;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20171015";}

	
	public Object t(Object obj) throws Exception
	{
		int[] t = (int[]) obj;
		int count = t.length;
		
		double sum = 0;
		for(int n:t) sum += Math.exp(n);
		
		double[] t1 = new double[count];
		for(int i=0;i<count;i++) t1[i] = Math.exp(t[i])/sum;
		return t1;
	}
}
