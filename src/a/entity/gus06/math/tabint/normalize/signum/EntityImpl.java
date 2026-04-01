package a.entity.gus06.math.tabint.normalize.signum;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151209";}



	
	
	public Object t(Object obj) throws Exception
	{
		int[] t = (int[]) obj;
		int count = t.length;
		
		int[] t1 = new int[count];
		for(int i=0;i<count;i++) t1[i] = Integer.signum(t[i]);
		return t1;
	}
}
