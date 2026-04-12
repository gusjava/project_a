package a.entity.gus06.string.transform.framework.doc.en.feature_h_use;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251203";}


	private Service get;

	public EntityImpl() throws Exception
	{
		get = Outside.service(this,"gus06.framework.doc.en.feature_h_use");
	}
	
	public Object t(Object obj) throws Exception
	{
		return get.g();
	}
}
