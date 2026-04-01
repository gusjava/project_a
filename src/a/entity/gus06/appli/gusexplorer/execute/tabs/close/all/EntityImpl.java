package a.entity.gus06.appli.gusexplorer.execute.tabs.close.all;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20151003";}


	private Service manager;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.data.manager");
	}
	
	
	public void e() throws Exception
	{
		manager.v("clear",null);
	}
}
