package a.entity.gus06.appli.dragontale.game.start;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20200517";}


	private Service playerData;
	private Service level0Start;
	
	public EntityImpl() throws Exception
	{
		playerData = Outside.service(this,"gus06.appli.dragontale.player.data");
		level0Start = Outside.service(this,"gus06.appli.dragontale.level0.start");
	}
	
	public void e() throws Exception
	{
		playerData.p("reset");
		level0Start.e();
	}
}
