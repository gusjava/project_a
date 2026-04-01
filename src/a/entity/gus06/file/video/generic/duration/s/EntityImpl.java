package a.entity.gus06.file.video.generic.duration.s;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201030";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.video.generic.duration");
	}
	
	public Object t(Object obj) throws Exception
	{
		Long duration = (Long) perform.t(obj);
		return Long.valueOf(Math.round(duration.longValue()*0.001));
	}
}