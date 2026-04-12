package a.entity.gus06.time.duration.between.s.fr;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180118";}


	private Service compute;
	private Service buildDisplay;


	public EntityImpl() throws Exception
	{
		compute = Outside.service(this,"gus06.time.duration.between.s");
		buildDisplay = Outside.service(this,"gus06.string.transform.format.duration.s.fr");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Long duration = (Long) compute.t(obj);
		return buildDisplay.t(duration);
	}
}
