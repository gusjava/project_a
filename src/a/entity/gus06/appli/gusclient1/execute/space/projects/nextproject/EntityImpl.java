package a.entity.gus06.appli.gusclient1.execute.space.projects.nextproject;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20141028";}


	private Service change;

	public EntityImpl() throws Exception
	{change = Outside.service(this,"gus06.appli.gusclient1.project.change");}
	
	
	public void e() throws Exception
	{change.p("1");}
}
