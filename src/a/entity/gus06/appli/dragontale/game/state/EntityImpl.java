package a.entity.gus06.appli.dragontale.game.state;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, P, G {

	public String creationDate() {return "20200516";}


	private int state = 0;


	public void p(Object obj) throws Exception
	{
		int newState = Integer.parseInt((String)obj);
		if(newState==state) return;
		
		state = newState;
		stateChanged();
	}



	public Object g() throws Exception
	{return ""+state;}


	
	
	private void stateChanged()
	{send(this,"stateChanged()");}
}
