package a.entity.gus06.math.tabint.width;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151209";}


	private Service range;


	public EntityImpl() throws Exception
	{
		range = Outside.service(this,"gus06.math.tabint.range");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		int[] r = (int[]) range.t(obj);
		return Integer.valueOf(r[1]-r[0]);
	}
}
