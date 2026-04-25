package a.entity.gus06.appli.dragontale.game.control;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, ActionListener {

	public String creationDate() {return "20200517";}


	private Service control;
	private Service state;
	private Service level0Controller;
	private Service startLevel;
	private Service startGame;
	private Service exit;



	public EntityImpl() throws Exception
	{
		control = Outside.service(this,"gus06.sys.gameengine1.control");
		state = Outside.service(this,"gus06.appli.dragontale.game.state");
		level0Controller = Outside.service(this,"gus06.appli.dragontale.level0.controller");
		startLevel = Outside.service(this,"gus06.appli.dragontale.game.startlevel");
		startGame = Outside.service(this,"gus06.appli.dragontale.game.start");
		exit = Outside.service(this,"gus.y.app1.execute.exit");
		
		control.addActionListener(this);
	}


	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("keyPressed()")) 
		handleKey();
	}
	
	
	private void handleKey()
	{
		try
		{
			String key = (String) control.r("lastKey");
			
			int val = Integer.parseInt((String) state.g());
			if(val==0)
			{
				level0Controller.p(key);
				return;
			}
			
			if(key.equals("F1")) startLevel.e();
			else if(key.equals("F2")) startGame.e();
			else if(key.equals("F3")) exit.e();
		}
		catch(Exception e)
		{Outside.err(this,"handleKey()",e);}
	}

}
