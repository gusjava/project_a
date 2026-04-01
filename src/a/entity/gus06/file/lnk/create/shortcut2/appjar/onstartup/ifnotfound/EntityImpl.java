package a.entity.gus06.file.lnk.create.shortcut2.appjar.onstartup.ifnotfound;

import a.framework.*;

public class EntityImpl implements Entity, E, F {

	public String creationDate() {return "20180309";}


	private Service create;
	private Service check;
	

	public EntityImpl() throws Exception
	{
		create = Outside.service(this,"gus06.file.lnk.create.shortcut2.appjar.onstartup");
		check = Outside.service(this,"gus06.file.lnk.check.insidedir.appjar.onstartup");
	}

	public void e() throws Exception
	{
		if(!check.f(null)) create.e();
	}
	
	public boolean f(Object obj) throws Exception
	{
		boolean found = check.f(null);
		if(!found) create.e();
		return !found;
	}
}
