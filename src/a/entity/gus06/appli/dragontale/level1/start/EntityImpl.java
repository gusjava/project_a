package a.entity.gus06.appli.dragontale.level1.start;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20200517";}
	
	public static final String LEVEL = "1";


	private Service mvtManager;
	private Service playerEngine;
	private Service levelEngine;
	private Service traveling;
	private Service state;
	
	public EntityImpl() throws Exception
	{
		mvtManager = Outside.service(this,"gus06.sys.phys2d.mvt.manager");
		playerEngine = Outside.service(this,"gus06.appli.dragontale.player.engine");
		levelEngine = Outside.service(this,"gus06.appli.dragontale.level1.engine");
		traveling = Outside.service(this,"gus06.appli.dragontale.level1.traveling");
		state = Outside.service(this,"gus06.appli.dragontale.game.state");
	}
	
	public void e() throws Exception
	{
		mvtManager.e();
		playerEngine.p("reset");
		levelEngine.p("reset");
		traveling.p("reset");
		state.p(LEVEL);
	}
}
