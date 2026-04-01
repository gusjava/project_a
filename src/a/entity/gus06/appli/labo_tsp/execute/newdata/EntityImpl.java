package a.entity.gus06.appli.labo_tsp.execute.newdata;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20190306";}


	private Service manager;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.labo_tsp.data.manager");
	}
	
	public void e() throws Exception
	{manager.e();}
}
