package a.entity.gus06.appli.gusclient1.execute.space.projects.deployproject;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140903";}


	private Service deploy;


	public EntityImpl() throws Exception
	{
		deploy = Outside.service(this,"gus06.appli.gusclient1.project.deploy");
	}
	
	
	public void e() throws Exception
	{
		deploy.e();
	}
}
