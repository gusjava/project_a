package a.entity.gus06.data.compare.string.comparator2.distance;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160806";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.compare.string.comparator2");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Double d = (Double) perform.t(obj);
		return Double.valueOf(1-d.doubleValue());
	}
}