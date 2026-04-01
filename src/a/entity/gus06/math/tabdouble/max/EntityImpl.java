package a.entity.gus06.math.tabdouble.max;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150708";}

	
	public Object t(Object obj) throws Exception
	{
		double[] t = (double[]) obj;
		double max = Double.MIN_VALUE;
		int count = t.length;
		
		for(int i=0;i<count;i++) if(t[i]>max) max = t[i];
		return Double.valueOf(max);
	}
}
