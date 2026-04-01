package a.entity.gus06.app.jarfile.listing.java.manager.gyem.modules;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140916";}


	private Service findList;
	
	public EntityImpl() throws Exception
	{findList = Outside.service(this,"gus06.app.jarfile.listing.java.manager.gyem.groups");}
	
	public Object g() throws Exception
	{return findList.r("modules");}
}
