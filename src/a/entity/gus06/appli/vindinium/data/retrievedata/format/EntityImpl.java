package a.entity.gus06.appli.vindinium.data.retrievedata.format;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170923";}
	
	private Service format_turns;
	private Service format_heros;
	private Service format_tiles;
	private Service format_me;
	
	public EntityImpl() throws Exception
	{
		format_turns = Outside.service(this,"gus06.appli.vindinium.data.retrievedata.format.turns");
		format_heros = Outside.service(this,"gus06.appli.vindinium.data.retrievedata.format.heros");
		format_tiles = Outside.service(this,"gus06.appli.vindinium.data.retrievedata.format.tiles");
		format_me = Outside.service(this,"gus06.appli.vindinium.data.retrievedata.format.me");
	}
	
	public void p(Object obj) throws Exception
	{
		format_turns.p(obj);
		format_tiles.p(obj);
		format_heros.p(obj);
		format_me.p(obj);
	}
}
