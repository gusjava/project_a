package a.entity.gus06.time.date.build.af0.day1;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20201107";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.time.date.add.days.fromnow");
	}
	
	public Object g() throws Exception
	{
		return perform.t(Integer.valueOf(1));
	}
}