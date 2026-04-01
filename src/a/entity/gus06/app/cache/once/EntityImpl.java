package a.entity.gus06.app.cache.once;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20170320";}


	private Service cache;


	public EntityImpl() throws Exception
	{
		cache = Outside.service(this,"gus06.app.cache");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		boolean found = cache.f(""+obj);
		cache.v(""+obj,"done");
		return !found;
	}
}
