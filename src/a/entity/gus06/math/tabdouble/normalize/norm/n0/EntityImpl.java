package a.entity.gus06.math.tabdouble.normalize.norm.n0;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191022";}


	private Service getMin;

	public EntityImpl() throws Exception
	{
		getMin = Outside.service(this,"gus06.math.tabdouble.min");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		double[] t = (double[]) obj;
		int count = t.length;
		
		double min = (double) getMin.t(t);
		
		double[] t1 = new double[count];
		for(int i=0;i<count;i++) t1[i] = t[i]-min;
		return t1;
	}
}
