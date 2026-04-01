package a.entity.gus06.appli.dragontale.game.startlevel;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20200517";}


	private Service state;
	private Service level1Start;
	
	public EntityImpl() throws Exception
	{
		state = Outside.service(this,"gus06.appli.dragontale.game.state");
		level1Start = Outside.service(this,"gus06.appli.dragontale.level1.start");
	}
	
	public void e() throws Exception
	{
		int val = Integer.parseInt((String) state.g());
		if(val==1) level1Start.e();
	}
}
