package a.entity.gus06.math.tabint.normalize.density;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151209";}


	private Service sumAbs;

	public EntityImpl() throws Exception
	{
		sumAbs = Outside.service(this,"gus06.math.tabint.sum.abs");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		int[] t = (int[]) obj;
		int count = t.length;
		
		double sum = (double) sumAbs.t(t);
		if(sum==0) sum = 1;
		
		double[] t1 = new double[count];
		for(int i=0;i<count;i++) t1[i] = t[i]/sum;
		return t1;
	}
}
