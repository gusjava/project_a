package a.entity.gus06.math.tabint.max;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150708";}

	
	public Object t(Object obj) throws Exception
	{
		int[] t = (int[]) obj;
		int max = Integer.MIN_VALUE;
		int count = t.length;
		
		for(int i=0;i<count;i++) if(t[i]>max) max = t[i];
		return Integer.valueOf(max);
	}
}
