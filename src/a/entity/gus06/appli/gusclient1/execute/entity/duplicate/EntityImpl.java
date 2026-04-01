package a.entity.gus06.appli.gusclient1.execute.entity.duplicate;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140801";}


	private Service selection;
	private Service duplicate;
	private Service input;
	private Service listingCache;

	
	public EntityImpl() throws Exception
	{
		selection = Outside.service(this,"gus06.appli.gusclient1.gui.entity.holder");
		duplicate = Outside.service(this,"gus06.command.entity.duplicate");
		input = Outside.service(this,"gus06.input.text.dialog");
		listingCache = Outside.service(this,"gus06.entitydev.listing1.cache");
	}
	
	
	public void e() throws Exception
	{
		String name = (String) selection.g();
		if(name==null) return;
		
		String newName = (String) input.t(new String[]{"Enter new name",name});
		if(newName==null || newName.equals("")) return;
		
		duplicate.p(new String[]{name,newName});
		listingCache.e();
	}
}
