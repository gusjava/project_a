package a.entity.gus06.math.tabint.normalize.norm.n1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191022";}


	private Service range;

	public EntityImpl() throws Exception
	{
		range = Outside.service(this,"gus06.math.tabint.range");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		int[] t = (int[]) obj;
		int count = t.length;
		
		int[] r = (int[]) range.t(t);
		int min = Math.abs(r[0]);
		int max = Math.abs(r[1]);
		double m = (double) Math.max(min,max);
		
		double[] t1 = new double[count];
		for(int i=0;i<count;i++) t1[i] = t[i]/m;
		return t1;
	}
}
