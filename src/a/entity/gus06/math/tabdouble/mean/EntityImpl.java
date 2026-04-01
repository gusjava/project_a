package a.entity.gus06.math.tabdouble.mean;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151230";}

	
	public Object t(Object obj) throws Exception
	{
		double[] t = (double[]) obj;
		int count = t.length;
		
		double sum = 0;
		for(int i=0;i<count;i++) sum += t[i];
		return Double.valueOf(sum/count);
	}
}
