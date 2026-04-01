package a.entity.gus06.string.transform.framework.sources;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251203";}


	private Service get;

	public EntityImpl() throws Exception
	{
		get = Outside.service(this,"gus06.framework.sources");
	}
	
	public Object t(Object obj) throws Exception
	{
		return get.r("*");
	}
}
