package a.entity.gus06.appli.dragontale.level1.draw;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200516";}


	private Service mvtManager;
	private Service playerEngine;
	private Service levelEngine;
	private Service travelling;
	
	private Service background;
	private Service drawTiles;
	private Service drawPlayer;
	
	

	public EntityImpl() throws Exception
	{
		mvtManager = Outside.service(this,"gus06.sys.phys2d.mvt.manager");
		playerEngine = Outside.service(this,"gus06.appli.dragontale.player.engine");
		levelEngine = Outside.service(this,"gus06.appli.dragontale.level1.engine");
		travelling = Outside.service(this,"gus06.appli.dragontale.level1.traveling");
		
		background = Outside.service(this,"gus06.appli.dragontale.level1.draw.background");
		drawTiles = Outside.service(this,"gus06.appli.dragontale.level1.draw.tiles");
		drawPlayer = Outside.service(this,"gus06.appli.dragontale.level1.draw.player");
	}



	public void p(Object obj) throws Exception
	{
		levelEngine.e();
		playerEngine.e();
		mvtManager.e();
		travelling.e();
		
		background.p(obj);
		drawTiles.p(obj);
		drawPlayer.p(obj);
	}
}