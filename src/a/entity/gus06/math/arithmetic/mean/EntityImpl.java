package a.entity.gus06.math.arithmetic.mean;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150516";}

	
	public Object t(Object obj) throws Exception
	{
		double[] t = (double[]) obj;
		double sum = 0;
		int count = t.length;
		
		for(int i=0;i<count;i++) sum += t[i];
		return Double.valueOf(sum/count);
	}
}
