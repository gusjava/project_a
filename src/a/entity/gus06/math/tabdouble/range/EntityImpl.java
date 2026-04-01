package a.entity.gus06.math.tabdouble.range;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151209";}

	
	public Object t(Object obj) throws Exception
	{
		double[] t = (double[]) obj;
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		int count = t.length;
		
		for(int i=0;i<count;i++)
		{
			if(t[i]>max) max = t[i];
			if(t[i]<min) min = t[i];
		}
		return new double[]{min,max};
	}
}
