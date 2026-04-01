package a.entity.gus06.math.tabint.range;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151209";}

	
	public Object t(Object obj) throws Exception
	{
		int[] t = (int[]) obj;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int count = t.length;
		
		for(int i=0;i<count;i++)
		{
			if(t[i]>max) max = t[i];
			if(t[i]<min) min = t[i];
		}
		return new int[]{min,max};
	}
}
