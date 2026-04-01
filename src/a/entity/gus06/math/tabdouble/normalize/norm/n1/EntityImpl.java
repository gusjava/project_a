package a.entity.gus06.math.tabdouble.normalize.norm.n1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191022";}


	private Service range;

	public EntityImpl() throws Exception
	{
		range = Outside.service(this,"gus06.math.tabdouble.range");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		double[] t = (double[]) obj;
		int count = t.length;
		
		double[] r = (double[]) range.t(t);
		double min = Math.abs(r[0]);
		double max = Math.abs(r[1]);
		double m = Math.max(min,max);
		
		double[] t1 = new double[count];
		for(int i=0;i<count;i++) t1[i] = t[i]/m;
		return t1;
	}
}
