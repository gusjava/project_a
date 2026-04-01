package a.entity.gus06.math.tabint.normalize.norm.n0;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191022";}


	private Service getMin;

	public EntityImpl() throws Exception
	{
		getMin = Outside.service(this,"gus06.math.tabint.min");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		int[] t = (int[]) obj;
		int count = t.length;
		
		int min = (int) getMin.t(t);
		
		int[] t1 = new int[count];
		for(int i=0;i<count;i++) t1[i] = t[i]-min;
		return t1;
	}
}
