package a.entity.gus06.appli.gusclient1.execute.space.projects.testproject;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140904";}


	private Service manager;
	private Service launch;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		launch = Outside.service(this,"gus06.appli.gusclient1.project.deploy.launchjar");
	}
	
	
	public void e() throws Exception
	{launch.e();}
}
