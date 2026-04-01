package a.entity.gus06.appli.vindinium.gui.configview;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20170923";}


	private Service myBot;
	private Service serverGame;
	private Service localGame;
	
	private JTabbedPane tab;


	public EntityImpl() throws Exception
	{
		myBot = Outside.service(this,"*gus06.appli.vindinium.gui.configview.mybot");
		serverGame = Outside.service(this,"*gus06.appli.vindinium.gui.configview.servergame");
		localGame = Outside.service(this,"*gus06.appli.vindinium.gui.configview.localgame");
		
		tab = new JTabbedPane();
		tab.addTab("My Bot",(JComponent) myBot.i());
		tab.addTab("Server Game",(JComponent) serverGame.i());
		tab.addTab("Local Game",(JComponent) localGame.i());
	}


	public Object i() throws Exception
	{return tab;}
}
