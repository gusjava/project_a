package a.entity.gus06.math.tabdouble.min;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150708";}

	
	public Object t(Object obj) throws Exception
	{
		double[] t = (double[]) obj;
		double min = Double.MAX_VALUE;
		int count = t.length;
		
		for(int i=0;i<count;i++) if(t[i]<min) min = t[i];
		return Double.valueOf(min);
	}
}
