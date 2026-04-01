package a.entity.gus06.math.tabint.normalize.norm2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151209";}


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
		int min = r[0];
		int max = r[1];
		
		double m = max-min;
		if(m==0) m = 1;
		
		int k = max+min;
		
		double[] t1 = new double[count];
		for(int i=0;i<count;i++) t1[i] = (2*t[i]-k)/m;
		return t1;
	}
}
