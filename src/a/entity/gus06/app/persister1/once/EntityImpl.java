package a.entity.gus06.app.persister1.once;

import a.framework.*;

public class EntityImpl implements Entity, F, V {

	public String creationDate() {return "20170320";}


	private Service persister1;


	public EntityImpl() throws Exception
	{
		persister1 = Outside.service(this,"gus06.app.persister1");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		persister1.v(key,obj);
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		boolean found = persister1.f(""+obj);
		persister1.v(""+obj,"done");
		return !found;
	}
}