package a.entity.gus06.math.tabint.normalize.reciprocal;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151210";}


	
	
	public Object t(Object obj) throws Exception
	{
		int[] t = (int[]) obj;
		int count = t.length;
		
		double[] t1 = new double[count];
		for(int i=0;i<count;i++) t1[i] = 1/(double) t[i];
		return t1;
	}
}
