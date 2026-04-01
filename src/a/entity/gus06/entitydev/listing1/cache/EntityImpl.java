package a.entity.gus06.entitydev.listing1.cache;

import a.framework.*;

public class EntityImpl implements Entity, G, E {

	public String creationDate() {return "20151109";}


	private Service listing1;
	
	private Object cache;


	public EntityImpl() throws Exception
	{
		listing1 = Outside.service(this,"gus06.entitydev.listing1");
	}
	
	
	public void e() throws Exception
	{
		cache = null;
	}
	
	
	public Object g() throws Exception
	{
		if(cache==null) cache = listing1.g();
		return cache;
	}
}
