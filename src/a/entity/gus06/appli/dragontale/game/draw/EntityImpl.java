package a.entity.gus06.appli.dragontale.game.draw;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200516";}


	private Service state;
	private Service drawLevel0;
	private Service drawLevel1;
	

	public EntityImpl() throws Exception
	{
		state = Outside.service(this,"gus06.appli.dragontale.game.state");
		drawLevel0 = Outside.service(this,"gus06.appli.dragontale.level0.draw");
		drawLevel1 = Outside.service(this,"gus06.appli.dragontale.level1.draw");
	}



	public void p(Object obj) throws Exception
	{draw().p(obj);}


	
	
	
	
	private P draw() throws Exception
	{
		int val = Integer.parseInt((String) state.g());
		switch(val)
		{
			case 0: return drawLevel0;
			case 1: return drawLevel1;
			default: throw new Exception("Unknown game state: "+val);
		}
	}
}
