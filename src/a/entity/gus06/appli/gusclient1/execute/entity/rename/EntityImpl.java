package a.entity.gus06.appli.gusclient1.execute.entity.rename;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140801";}


	private Service selection;
	private Service rename;
	private Service input;
	private Service isOwn;
	private Service listingCache;
	

	public EntityImpl() throws Exception
	{
		selection = Outside.service(this,"gus06.appli.gusclient1.gui.entity.holder");
		rename = Outside.service(this,"gus06.command.entity.rename");
		input = Outside.service(this,"gus06.input.text.dialog");
		isOwn = Outside.service(this,"gus06.app.entity.checkname.own");
		listingCache = Outside.service(this,"gus06.entitydev.listing1.cache");
	}
	
	
	public void e() throws Exception
	{
		String name = (String) selection.g();
		if(name==null) return;
//		if(!isOwn.f(name)) return;
		
		String newName = (String) input.t(new String[]{"Enter new name",name});
		if(newName==null || newName.equals("")) return;
		
		rename.p(new String[]{name,newName});
		listingCache.e();
	}
}
