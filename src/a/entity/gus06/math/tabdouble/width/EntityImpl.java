package a.entity.gus06.math.tabdouble.width;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151209";}


	private Service range;


	public EntityImpl() throws Exception
	{
		range = Outside.service(this,"gus06.math.tabdouble.range");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		double[] r = (double[]) range.t(obj);
		return Double.valueOf(r[1]-r[0]);
	}
}
