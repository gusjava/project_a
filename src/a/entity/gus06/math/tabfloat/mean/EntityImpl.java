package a.entity.gus06.math.tabfloat.mean;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160705";}

	
	public Object t(Object obj) throws Exception
	{
		float[] t = (float[]) obj;
		float sum = 0;
		int count = t.length;
		
		for(int i=0;i<count;i++) sum += t[i];
		return Float.valueOf(sum/count);
	}
}
