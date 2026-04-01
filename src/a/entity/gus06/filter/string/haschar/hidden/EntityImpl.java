package a.entity.gus06.filter.string.haschar.hidden;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190316";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.string.hiddenchars.couriernew.asbool");}

	public boolean f(Object obj) throws Exception
	{return perform.f(obj);}
}
