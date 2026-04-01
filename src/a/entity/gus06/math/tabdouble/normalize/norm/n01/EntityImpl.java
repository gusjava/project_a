package a.entity.gus06.math.tabdouble.normalize.norm.n01;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151210";}


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
		double min = r[0];
		double max = r[1];
		
		double m = max-min;
		if(m==0) m = 1;
		
		double[] t1 = new double[count];
		for(int i=0;i<count;i++) t1[i] = (t[i]-min)/m;
		return t1;
	}
}
