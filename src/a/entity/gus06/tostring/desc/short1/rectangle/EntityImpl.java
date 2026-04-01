package a.entity.gus06.tostring.desc.short1.rectangle;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180219";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.tostring.rectangle");
	}

	public Object t(Object obj) throws Exception
	{
		return "Rectangle: "+perform.t(obj);
	}
}
