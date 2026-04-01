package a.entity.gus06.math.tabint.variance;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160705";}

	
	public Object t(Object obj) throws Exception
	{
		int[] t = (int[]) obj;
		int count = t.length;
		
		double sum = 0;
		for(int i=0;i<count;i++) sum += t[i];
		double avg = sum/count;
		
		sum = 0;
		for(int i=0;i<count;i++) sum += Math.pow(t[i]-avg,2);
		return Double.valueOf(sum/count);
	}
}
