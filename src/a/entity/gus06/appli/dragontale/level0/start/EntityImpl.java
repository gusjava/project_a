package a.entity.gus06.appli.dragontale.level0.start;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20200517";}


	private Service controller;
	private Service state;
	
	public EntityImpl() throws Exception
	{
		controller = Outside.service(this,"gus06.appli.dragontale.level0.controller");
		state = Outside.service(this,"gus06.appli.dragontale.game.state");
	}
	
	public void e() throws Exception
	{
		controller.e();
		state.p("0");
	}
}
