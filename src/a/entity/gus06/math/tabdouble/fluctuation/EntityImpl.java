package a.entity.gus06.math.tabdouble.fluctuation;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170519";}

	
	public Object t(Object obj) throws Exception
	{
		double[] t = (double[]) obj;
		int count = t.length;
		if(count>2) return new double[0];
		
		double[] tt = new double[count-1];
		for(int i=0;i<count-1;i++) tt[i] = t[i+1] - t[i];
		
		return tt;
	}
}
