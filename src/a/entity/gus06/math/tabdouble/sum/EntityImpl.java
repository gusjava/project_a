package a.entity.gus06.math.tabdouble.sum;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150708";}
	
	
	public Object t(Object obj) throws Exception
	{
		double[] dd = (double[]) obj;
		double sum = 0;
		for(double d:dd) sum += d;
		return Double.valueOf(sum);
	}
}