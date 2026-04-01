package a.entity.gus06.appli.gusclient1.execute.entity.remove;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140801";}


	private Service selection;
	private Service remove;
	private Service confirm;
	private Service isOwn;
	private Service listingCache;

	public EntityImpl() throws Exception
	{
		selection = Outside.service(this,"gus06.appli.gusclient1.gui.entity.holder");
		remove = Outside.service(this,"gus06.command.entity.remove");
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
		isOwn = Outside.service(this,"gus06.app.entity.checkname.own");
		listingCache = Outside.service(this,"gus06.entitydev.listing1.cache");
	}
	
	
	public void e() throws Exception
	{
		String name = (String) selection.g();
		if(name==null) return;
//		if(!isOwn.f(name)) return;
		
		if(confirm.f("Removing entity "+name+" ?"))
		remove.p(name);
		listingCache.e();
	}
}
