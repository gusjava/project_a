package a.entity.gus06.math.statistics.compute.standarddeviation;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150516";}

	
	public Object t(Object obj) throws Exception
	{
		double[] t = (double[]) obj;
		int count = t.length;
		
		double sum = 0;
		for(int i=0;i<count;i++) sum += t[i];
		double mean = sum/count;
		
		sum = 0;
		for(int i=0;i<count;i++)
		{
			double d = t[i]-mean;
			sum += d*d;
		}
		return Double.valueOf(Math.sqrt(sum/count));
	}
}
