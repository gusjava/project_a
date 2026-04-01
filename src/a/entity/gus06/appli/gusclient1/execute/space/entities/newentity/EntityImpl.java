package a.entity.gus06.appli.gusclient1.execute.space.entities.newentity;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140812";}

	private Service generate;
	private Service input;
	private Service listingCache;

	
	public EntityImpl() throws Exception
	{
		generate = Outside.service(this,"gus06.command.entity.generate");
		input = Outside.service(this,"gus06.input.text.dialog");
		listingCache = Outside.service(this,"gus06.entitydev.listing1.cache");
	}
	
	public void e() throws Exception
	{
		String name = (String) input.t("Enter new name");
		if(name==null) return;
		
		generate.p(name);
		listingCache.e();
	}
}
