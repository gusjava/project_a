package a.entity.gus06.math.tab.number.sum;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151028";}

	
	public Object t(Object obj) throws Exception
	{
		Number[] t = (Number[]) obj;
		double sum = 0;
		int count = t.length;
		
		for(int i=0;i<count;i++) sum += t[i].doubleValue();
		return Double.valueOf(sum);
	}
}
